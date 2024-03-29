package com.sera.donemsonuc.service;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


import net.sf.jasperreports.engine.JRDataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sera.cevreselfaktor.dao.CevreselFaktorDao;
import com.sera.dao.CenvDegerListeDao;
import com.sera.dao.CenvGirisDao;
import com.sera.dao.CenvSabitlerDao;
import com.sera.donemsonuc.dao.DonemSonucDao;
import com.sera.gaz.dao.GazDao;
import com.sera.gaz.model.Gaz;
import com.sera.hesaplama.dao.HesaplamaDao;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvDonemSonuc;
import com.sera.model.SeraCenvGiris;
import com.sera.model.SeraCenvHesaplama;
import com.sera.model.SeraCenvSabitler;
import com.sera.util.DonemSonucRaporParams;
import com.util.constant.ApplicationConstants;
import com.membership.dao.LoginDao;
import com.membership.model.User;

@Service("donemsonucService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DonemSonucServiceImpl implements DonemSonucService{

	@Autowired
	private DonemSonucDao donemsonucdao;
	
	@Autowired
	private CenvGirisDao girisdao;
	
	@Autowired
	private CenvDegerListeDao degerlistedao;
	
	@Autowired
	private CenvSabitlerDao sabitlerdao;
	
	@Autowired
	private HesaplamaDao hesaplamadao;
	
	@Autowired
	private GazDao gazdao;
	
	@Autowired
	private CevreselFaktorDao cevreselfaktordao;

	public DonemSonucServiceImpl() {
	}

	@Override
	public boolean isControlGirisFull(String donem) {
		//sistemdeki elle değer girilen yaprak sayısı
		int quantityYaprakElle=degerlistedao.getYaprakQuantity("Elle");
		//belirli döneme ait girilen değer sayısı
		int quantityGiris=girisdao.getGirisQuantity(donem);
		//iki değer de eşit ise anlayacağız ki yaprakların değerleri o döneme girilmiş.
		if(quantityGiris==quantityYaprakElle)
			return true;
		else
		    return false;
	}

	@Override
	public boolean isDonemSonucExist(String donem) {
		// TODO Auto-generated method stub
		return donemsonucdao.isDonemSonucExist(donem);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false) 
	public void fillDonemSonuc(String donem) {
		
		List<SeraCenvDegerListe> enAltDallar=degerlistedao.ListEnAltDal();
		List<SeraCenvDonemSonuc> listeDonemSonuc;
		List<SeraCenvDegerListe> dalveKokler;
	    SeraCenvDonemSonuc donemsonuctemp;
		Double sonuc;
		/*
		 * 1 en alt dallar içinde dön
		 * 2 her dalın hesaplama tablosunda karşılığını bul
		 * 3 bu hesaplamaların idlerinin karşılığını değer giriş tablosundan ve 
		 * çevresel faktör sayfasından bul
		 * 4 bu hesaplama işini bitirip dönem sonuç tablosuna kaydet
		 * 5 en alt dallar bittikten sonra içiçe dönen şekilde yöntemi çalıştırıp köke kadar git
		 * 
		 * bu işlemler için gereken kontroller:
		 * 1 ağaç yapısı tam olacak, yani sabit ile elle olan değerler girilmiş olacak
		 * 2 çevresel faktörler girilmiş olacak
		 * 3 en alt dallara ait tüm hesaplamalar bitmiş olacak
		 *
		 */
		for(int i=0;i<enAltDallar.size();i++){
			//elimizdeki en alt dala ait tüm hesaplama listesi mevcut
			double hesapsonuc=0,toplamhesapsonuc=0;
			List<SeraCenvHesaplama> hesaplamalar=hesaplamadao.listHesaplama(enAltDallar.get(i).getId());
			//hesaplamalar tablosundan sıra ile çek
			for(int j=0;j<hesaplamalar.size();j++){
				try {
					hesapsonuc=HesapCozumle(hesaplamalar.get(j).getHesaplama(),donem);
				} catch (ScriptException e) {
					e.printStackTrace();
				}
				//sonuç elimide en alt dal da elimizde şimdi dönem sonuç kaydını tamamla
				SeraCenvDonemSonuc donemsonuc=new SeraCenvDonemSonuc();
			    donemsonuc.setSonuc(hesapsonuc);
			    donemsonuc.setBaslikId(enAltDallar.get(i).getId());
			    donemsonuc.setDonem(donem);
			    donemsonuc.setDeger(hesaplamalar.get(j).getDetay());
			    donemsonucdao.saveDonemSonuc(donemsonuc);
			    toplamhesapsonuc=toplamhesapsonuc+hesapsonuc;
			}
			//her gaz için ayrı ayrı değerini girdikten sonra bir de toplam değerini gireceğiz
				SeraCenvDonemSonuc donemsonuc2=new SeraCenvDonemSonuc();
			    donemsonuc2.setSonuc(toplamhesapsonuc);
			    donemsonuc2.setBaslikId(enAltDallar.get(i).getId());
			    donemsonuc2.setDonem(donem);
			    donemsonuc2.setDeger("TOPLAM");
			    donemsonucdao.saveDonemSonuc(donemsonuc2);
			   
		}
		
		//kaydedilen dönem sonuç kayıtlarını çek
		listeDonemSonuc=donemsonucdao.listDonemSonuc(donem);
		
		//elle girilen değerleri yukarı doğru dallara ve köke ekle
		//başla
		//bu olay devre dışı öncelikle en alt dalların değerlerini hesaplayacağız daha sonra bu mantık ile 
		//yapraktan değil de en alt daldan başlayacağız.
		
		for(int i=0;i<listeDonemSonuc.size();i++){
			dalveKokler=degerlistedao.getAncestors(listeDonemSonuc.get(i).getBaslikId());
			//elimizdeki sabit değerli yaprakın tüm dedelerini çektik ve şimdi
			//onlara bundaki değeri ekleyeceğiz
			for(int j=0;j<dalveKokler.size();j++){
				//kayıt var mı diye kontrol et
				donemsonuctemp=donemsonucdao.getDonemSonuc(donem, dalveKokler.get(j).getId(),
						listeDonemSonuc.get(i).getDeger());
				//kaydet
				
				
						if(donemsonuctemp==null){
							SeraCenvDonemSonuc donemsonuc=new SeraCenvDonemSonuc();
						    donemsonuc.setSonuc(listeDonemSonuc.get(i).getSonuc());
						    donemsonuc.setBaslikId(dalveKokler.get(j).getId());
						    donemsonuc.setDonem(donem);
						    donemsonuc.setDeger(listeDonemSonuc.get(i).getDeger());
						    donemsonucdao.saveDonemSonuc(donemsonuc);
					    //güncelle
						}else{
							//mevcut sonucu al
							sonuc=donemsonuctemp.getSonuc();
							sonuc=sonuc+listeDonemSonuc.get(i).getSonuc();
							donemsonuctemp.setSonuc(sonuc);	
							updateDonemSonuc(donemsonuctemp);
							
						}    	    
			}
		}
			
		//bitir
		
	}
	/*
	*bu yöntem hesaplama tablosundaki hesaplama alanındaki id leri değerleri ile değiştirip
	*sonuç döndürecek
	*/
	public double HesapCozumle(String hesap,String donem) throws ScriptException{
		int basnerede=0,sonnerede=0;
		String mevcuteleman,yenieleman;
		long eldekiid;
		Double eldekideger,deger;
		
		//boş kutuları sil yani i li olanları
		while (hesap.indexOf("i")!=-1){
	    	//faktörü bulduk yani elemanın ilk adresini
	    	basnerede=hesap.indexOf("i");
	    	//bundan sonraki @ işaretini bul yani elemanın bittiği yeri
	    	sonnerede=hesap.indexOf("@", basnerede);
	    	//elemanımızı elde ettik
	    	mevcuteleman=hesap.substring(basnerede, sonnerede);
		    	
	    	//şimdi de örneğin f12 yerine f24.5 yazacağız
	    	hesap=hesap.replaceAll(mevcuteleman, "");
	    }
		
		//önce tüm çevresel faktörleri al
		while (hesap.indexOf("f")!=-1){
	    	//faktörü bulduk yani elemanın ilk adresini
	    	basnerede=hesap.indexOf("f");
	    	//bundan sonraki @ işaretini bul yani elemanın bittiği yeri
	    	sonnerede=hesap.indexOf("@", basnerede);
	    	//elemanımızı elde ettik
	    	mevcuteleman=hesap.substring(basnerede, sonnerede);
	    	//bu elemanın değerini bulup hesap stringine koy
	    	eldekiid=Long.parseLong(mevcuteleman.substring(1));
	    	//şimdi bu idnin karşılığını bul
	    	eldekideger=cevreselfaktordao.getCevreselFaktor(eldekiid).getDeger();
	    	//şimdi de örneğin f12 yerine f24.5 yazacağız
	    	hesap=hesap.replaceAll(mevcuteleman, String.valueOf(eldekideger));
	    }
		//şimdi tüm yaprakları al
		while (hesap.indexOf("y")!=-1){
	    	//faktörü bulduk yani elemanın ilk adresini
	    	basnerede=hesap.indexOf("y");
	    	//bundan sonraki @ işaretini bul yani elemanın bittiği yeri
	    	sonnerede=hesap.indexOf("@", basnerede);
	    	//elemanımızı elde ettik
	    	mevcuteleman=hesap.substring(basnerede, sonnerede);
	    	//bu elemanın değerini bulup hesap stringine koy
	    	eldekiid=Long.parseLong(mevcuteleman.substring(1));
		    	//şimdi bu idnin karşılığını bul
		    	//tabi bulmadan önce sabit ise sabit tablosundan bakman lazım
		    	if(degerlistedao.getCenvDeger(eldekiid).gettip2().toString().equals("Elle")){
		    		//giriş tablosundan çek
		    		eldekideger=girisdao.getGirisKayit(donem, eldekiid).getDeger();
		    	}else{
		    		//şimdi sabitler tablosundan çek
		    		eldekideger=sabitlerdao.getSabit(eldekiid).getsabit();
		    	}
	    	
	    	//şimdi de örneğin f12 yerine f24.5 yazacağız
	    	hesap=hesap.replaceAll(mevcuteleman, String.valueOf(eldekideger));
	    }
		
		//yaprak ve çevresel faktörler bittikten sonra şimdi de tüm @ işaretlerini kaldır
		hesap=hesap.replaceAll("@", "");
		//elimizde matematiksel bi işlem stringi var bunu oku
		ScriptEngineManager mgr=new ScriptEngineManager();
		ScriptEngine engine=mgr.getEngineByName("JavaScript");
		
		
		return Double.parseDouble(engine.eval(hesap).toString());
		
		//return Long.parseLong(String.valueOf(engine.eval(hesap)));
	}

	@Override
	public double GetirEnAltDalHesap(Long id, String donem) {
		
		return 0;
	}

	@Override
	public boolean HesaplamaKayitKontrol() {
		//en alt dalları getir
		List<SeraCenvDegerListe> enAltDallar=degerlistedao.ListEnAltDal();
		int kontrol=1;
		SeraCenvHesaplama sonucKontrol;
		//şimdi de sistemde kayıtlı olan gazların listesini alacağız
		List<Gaz> gazlar=gazdao.listGaz();
		//en alt dalların idlerini kontrol et. her id ye ait 3 er tane kayıt olmalı
		for(int i=0;i<enAltDallar.size();i++){
			//şimdi her gaz için kayıtları kontrol et
			for(int j=0;j<gazlar.size();j++){
				sonucKontrol=hesaplamadao.getRow(gazlar.get(j).getName(), enAltDallar.get(i).getId());
				//eğer sonuç null çıkarsa sonuç false dönmeli
				if(sonucKontrol==null){
					kontrol=-1;
					//olumsuz bir sonuç alır almaz çıkmamız lazım
					j=gazlar.size();
					i=enAltDallar.size();
				}
			}
				
		}
		
		if(kontrol==-1){
			return false;
		}else{
			return true;
		}
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateDonemSonuc(SeraCenvDonemSonuc donemsonuc) {
		donemsonucdao.updateDonemSonuc(donemsonuc);
		
	}

	@Override
	public void deleteDonemSonuc(String donem) {
		donemsonucdao.deleteDonemSonuc(donem);
		
	}
	
	@Override
	public JRDataSource getCenvDonemSonucReport(DonemSonucRaporParams params) {
		// TODO Auto-generated method stub
		return donemsonucdao.getDonemSonucRep(params);
		
	}
	
}