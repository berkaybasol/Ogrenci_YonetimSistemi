package Ogrenci_Yonetim_Sistemi;
/*
Soruda istenilen menüler aşağıdaki gibidir.
Yapı tamamen size bırakılmıştır.
Aşağıdaki işlemlerin başarılı bir şekilde yapılması gerekmektedir.
Öğrenci için 4 bilgi yeterlidir. id, isim, ders, not. Yine 5 öğrencilik bir kapasite sağlanması yeterli olacaktır.

System.out.println("Öğrenci Not Yönetim Sistemi");
System.out.println("1. Öğrenci Ekle");
System.out.println("2. Öğrencileri Listele");
System.out.println("3. Öğrenci Notunu Güncelle");
System.out.println("4. Ortalama Hesapla");
System.out.println("5. Öğrenci Sil");
System.out.println("6. Belirli Notun Altındaki Öğrencileri Listele");
System.out.println("0. Çıkış");
Proje bittikten sonra gruplardan bir kişi github hesabına projenin tamamını yükleyip, linkini whatsapp üzerinden bana göndersin.
 */

import java.util.Arrays;
import java.util.Scanner;


public class OgrenciYonetimSistemi {
	static Scanner scanner = new Scanner(System.in);
	static final int OGRENCI_KAPASITESI = 5;
	static final int OGRENCI_BILGILERI = 4;
	static int ogrenciSayisi;
	static String[][] ogrenciler = new String[OGRENCI_KAPASITESI][OGRENCI_BILGILERI];
	
	public static void main(String[] args) {
		islemler();
	}
	
	public static void menu() {
		System.out.println("""
				                   **************************************************
				                   Öğrenci Not Yönetim Sistemi
				                   1. Öğrenci Ekle
				                   2. Öğrencileri Listele
				                   3. Öğrenci Notunu Güncelle
				                   4. Ortalama Hesapla
				                   5. Öğrenci Sil
				                   6. Belirli Notun Altındaki Öğrencileri Listele
				                   0. Çıkış
				                   """);
	}
	
	public static int getSecim() {
		menu();
		return scanner.nextInt();
	}
	
	public static void islemler() {
		while (true) {
			switch (getSecim()) {
				case 1 -> ogrenciEkle();
				case 2 -> ogrenciListele();
				case 3 -> ogrenciNotunuGuncelle();
				case 4 -> System.out.println(ortalamaHesapla());
				case 5 -> ogrenciSil();
				case 6 -> kalanlariListele();
				case 0 -> {
					System.out.println("Çıkış yapılıyor...");
					return;
				}
				default -> System.out.println("Geçersiz işlem yaptınız lütfen tekrar deneyin.");
			}
		}
		
	}
	
	
	private static void kalanlariListele() {
		System.out.println("Not sınırını giriniz: ");
		double can = scanner.nextDouble();
		boolean ogrenciBulunduMu = false;
		System.out.printf("%-5s %-20s %-20s %-10s%n", "ID", "İsim", "Ders", "Not");
		
		for (int i = 0; i < ogrenciSayisi; i++) {
			double ogrenciNotu = Double.parseDouble(ogrenciler[i][3]);
			
			if (ogrenciNotu < can) {
				ogrenciBulunduMu = true;
			
				System.out.printf("%-5s %-20s %-20s %-10s%n", ogrenciler[i][0], ogrenciler[i][1], ogrenciler[i][2], ogrenciler[i][3]);
			}
			if (!ogrenciBulunduMu) {
				System.out.println("Belirtilen notun altında öğrenci bulunamadı.");
				
			}
		}
	}
	
	
	private static void ogrenciSil() {
		System.out.print("Silmek istediğiniz öğrencinin ID'sini girin: ");
		int girilenId = scanner.nextInt();
		boolean ogrenciBulunduMu = false;
		
	
		for (int i = 0; i < ogrenciSayisi; i++) {
			if (Integer.parseInt(ogrenciler[i][0]) == girilenId) {
				ogrenciBulunduMu = true;
				
				
				for (int j = i; j < ogrenciSayisi - 1; j++) {
					ogrenciler[j] = ogrenciler[j + 1];
				}
				ogrenciler[ogrenciSayisi - 1] = null;
				ogrenciSayisi--;
				
				
				for (int k = i; k < ogrenciSayisi; k++) {
					ogrenciler[k][0] = String.valueOf(k + 1);
				}
				
				System.out.println("Öğrenci başarıyla silindi.");
				break;
			}
		}
		
		
		if (!ogrenciBulunduMu) {
			System.out.println("Öğrenci bulunamadı.");
		}
	}
	
	
	private static double ortalamaHesapla() {
	double toplam = 0;
		for (int i = 0; i <ogrenciSayisi ; i++) {
			toplam += Integer.parseInt(ogrenciler[i][3]);
		}
		double  ortalama = toplam / ogrenciSayisi;
		return ortalama;
	}
	
	private static void ogrenciNotunuGuncelle() {
		System.out.println("Notunu güncellemek istediğiniz öğrencinin id'sini giriniz: ");
		int girilenId = scanner.nextInt();
		boolean ogrenciBulunduMu = false;
		for (int i = 0; i <ogrenciSayisi ; i++) {
			if (Integer.parseInt(ogrenciler[i][0] )== girilenId) {
				ogrenciBulunduMu = true;
				
				if (ogrenciBulunduMu) {
					System.out.println("Yeni notu giriniz: ");
					double yeniNot = scanner.nextDouble();
					ogrenciler[i][3] = (String.valueOf(yeniNot));
					System.out.println("Not başarıyla güncellendi.");
				}
				
			}
			else{
				System.out.println("Öğrenci bulunamadı.");
			}
		}
	}
	
	private static void ogrenciEkle() {
		scanner.nextLine();
		System.out.println("----Öğrenci Ekle Menüsü----");
		if (OGRENCI_KAPASITESI < ogrenciSayisi) {
			System.out.println("Kapasite Dolu. Öğrenci eklenemedi.");
			return;
		}
		ogrenciler[ogrenciSayisi][0] = String.valueOf(ogrenciSayisi + 1);
		System.out.println("Öğrenci adını giriniz: ");
		ogrenciler[ogrenciSayisi][1] = scanner.nextLine();
		System.out.println("Ders adını giriniz:");
		ogrenciler[ogrenciSayisi][2] = scanner.nextLine();
		System.out.println("Öğrenci notunu giriniz");
		ogrenciler[ogrenciSayisi][3] = scanner.nextLine();
		ogrenciSayisi++;
		System.out.println("Öğrenci başarıyla eklendi.");
		
	}
	
	private static void ogrenciListele() {
		scanner.nextLine();
		
		System.out.println("Öğrenci Listesi");
		
		if (ogrenciSayisi == 0) {
			System.out.println("Listelenecek Öğrenci Bulunamadı.");
			return;
		}
		
		System.out.printf("%-5s %-20s %-20s %-10s%n", "ID", "Öğrenci", "Ders", "Not");
		
		for (int i = 0; i < ogrenciSayisi; i++) {
			System.out.printf("%-5s %-20s %-20s %-10s%n", ogrenciler[i][0], ogrenciler[i][1], ogrenciler[i][2], ogrenciler[i][3]);
		}
		
		
	}
	
	
	
	
	
}