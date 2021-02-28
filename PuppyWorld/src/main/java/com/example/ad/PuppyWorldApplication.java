package com.example.ad;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ad.domain.Post;
import com.example.ad.domain.PostComment;
import com.example.ad.domain.PostType;
import com.example.ad.domain.Product;
import com.example.ad.domain.ProductType;
import com.example.ad.domain.Provider;
import com.example.ad.domain.Reservation;
import com.example.ad.domain.User;
import com.example.ad.domain.Role;

import com.example.ad.domain.Services;
import com.example.ad.domain.Status;

import com.example.ad.repo.PostCommentRepository;

import com.example.ad.repo.PostRepository;
import com.example.ad.repo.ProductRepository;
import com.example.ad.repo.ProviderRepository;
import com.example.ad.repo.ReservationRepository;
import com.example.ad.repo.ServiceRepository;
import com.example.ad.repo.UserRepository;

@SpringBootApplication
public class PuppyWorldApplication {

	@Autowired
	public UserRepository urepo;

	@Autowired
	public ProductRepository prepo;

	@Autowired
	public ServiceRepository srepo;

	@Autowired
	public ProviderRepository prrepo;

	@Autowired
	public ReservationRepository resrepo;

	@Autowired
	public PostRepository ptrepo;
	
	@Autowired
	public PostCommentRepository pcrepo;

	public static void main(String[] args) {
		SpringApplication.run(PuppyWorldApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {

//			----- Users ------

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword1 = passwordEncoder.encode("password");


			User u1 = new User(Role.ROLE_ADMIN, "joe", "Joe", encodedPassword1, "e0533420@u.nus.edu");
			urepo.save(u1);
			
			User u2 = new User(Role.ROLE_ADMIN, "ann", "Ann", encodedPassword1, "chenyihan@gmail.com");
			urepo.save(u2);

			User u3 = new User(Role.ROLE_USER, "jay", "Jason", encodedPassword1, "jay@email.com");
			urepo.save(u3);
			
			User u4 = new User(Role.ROLE_USER, "dick", "Richard", encodedPassword1, "dick@email.com");
			urepo.save(u4);
			
			User u5 = new User(Role.ROLE_USER, "cass", "Cassandra", encodedPassword1, "cass@email.com");
			urepo.save(u5);
			
			User u6 = new User(Role.ROLE_USER, "steph", "Stephanie", encodedPassword1, "steph@email.com");
			urepo.save(u6);
			
			User u7 = new User(Role.ROLE_USER, "timmy", "Timothy", encodedPassword1, "tim@email.com");
			urepo.save(u7);
			
			
//			----- Products ------
			
			BufferedImage bImage = ImageIO.read(new File("src/main/resources/static/images/product1.jpg"));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bImage, "jpg", bos);
			byte[] img1 = bos.toByteArray();

			Product p1 = new Product("PRONATURE - HOLISTIC DOG SENIOR WHITEFISH & RICE 13.6kg", (float) 149.00, 23,
					ProductType.FOOD, img1, u1);
			prepo.save(p1);

			BufferedImage bImage2 = ImageIO.read(new File("src/main/resources/static/images/product2.jpg"));
			ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
			ImageIO.write(bImage2, "jpg", bos2);
			byte[] img2 = bos2.toByteArray();

			Product p2 = new Product("FISH 4 DOGS - FINEST SALMON COMPLETE (SMALL BITE) 1.5kg ", (float) 30.60, 52,
					ProductType.FOOD, img2, u2);
			prepo.save(p2);

			BufferedImage bImage3 = ImageIO.read(new File("src/main/resources/static/images/product3.jpg"));
			ByteArrayOutputStream bos3 = new ByteArrayOutputStream();
			ImageIO.write(bImage3, "jpg", bos3);
			byte[] img3 = bos3.toByteArray();

			Product p3 = new Product("PRONATURE - HOLISTIC DOG SENIOR WHITEFISH & RICE 12kg", (float) 115.00, 12,
					ProductType.FOOD, img3, u2);
			prepo.save(p3);

			BufferedImage bImage4 = ImageIO.read(new File("src/main/resources/static/images/product2.jpg"));
			ByteArrayOutputStream bos4 = new ByteArrayOutputStream();
			ImageIO.write(bImage4, "jpg", bos4);
			byte[] img4 = bos4.toByteArray();

			Product p4 = new Product("FISH 4 DOGS - FINEST SALMON COMPLETE 12kg", (float) 172.00, 6, ProductType.FOOD,
					img4, u1);
			prepo.save(p4);

			BufferedImage bImage5 = ImageIO.read(new File("src/main/resources/static/images/product5.jpg"));
			ByteArrayOutputStream bos5 = new ByteArrayOutputStream();
			ImageIO.write(bImage5, "jpg", bos5);
			byte[] img5 = bos5.toByteArray();

			Product p5 = new Product("BURP - LAMB WITH SALMON HYPOALLERGENIC FOR ADULT DOG 12kg ", (float) 100.00, 13,
					ProductType.FOOD, img5, u2);
			prepo.save(p5);

			BufferedImage bImage6 = ImageIO.read(new File("src/main/resources/static/images/product6.jpg"));
			ByteArrayOutputStream bos6 = new ByteArrayOutputStream();
			ImageIO.write(bImage6, "jpg", bos6);
			byte[] img6 = bos6.toByteArray();

			Product p6 = new Product("CANINE CAVIAR LIMITED INGREDIENT DIET - LAMB (ALKALINE) 10kg", (float) 159.50, 7,
					ProductType.FOOD, img6, u2);
			prepo.save(p6);

			BufferedImage bImage7 = ImageIO.read(new File("src/main/resources/static/images/product7.jpg"));
			ByteArrayOutputStream bos7 = new ByteArrayOutputStream();
			ImageIO.write(bImage7, "jpg", bos7);
			byte[] img7 = bos7.toByteArray();

			Product p7 = new Product("1ST CHOICE DOG SENIOR - LAMB & FISH, SENSITIVE SKIN & COAT 12kg", (float) 115.00,
					12, ProductType.FOOD, img7, u2);
			prepo.save(p7);

			BufferedImage bImage8 = ImageIO.read(new File("src/main/resources/static/images/product7.jpg"));
			ByteArrayOutputStream bos8 = new ByteArrayOutputStream();
			ImageIO.write(bImage8, "jpg", bos8);
			byte[] img8 = bos8.toByteArray();

			Product p8 = new Product("1ST CHOICE DOG SENIOR - LAMB & FISH, SENSITIVE SKIN & COAT 2.72kg", (float) 36.20,
					12, ProductType.FOOD, img8, u2);
			prepo.save(p8);

			BufferedImage bImage9 = ImageIO.read(new File("src/main/resources/static/images/product3.jpg"));
			ByteArrayOutputStream bos9 = new ByteArrayOutputStream();
			ImageIO.write(bImage9, "jpg", bos9);
			byte[] img9 = bos9.toByteArray();

			Product p9 = new Product("PRONATURE HOLISTIC DOG ADULT SALMON & RICE 13.6kg ", (float) 149.00, 32,
					ProductType.FOOD, img9, u2);
			prepo.save(p9);

			BufferedImage bImage10 = ImageIO.read(new File("src/main/resources/static/images/1.jpg"));
			ByteArrayOutputStream bos10 = new ByteArrayOutputStream();
			ImageIO.write(bImage10, "jpg", bos10);
			byte[] img10 = bos10.toByteArray();

			Product p10 = new Product("HILL'S SCIENCE DIET CANINE ADULT SENSITIVE SKIN & STOMACH 30lbs", (float) 149.25,
					22, ProductType.FOOD, img10, u2);
			prepo.save(p10);

			BufferedImage bImage11 = ImageIO.read(new File("src/main/resources/static/images/product11.jpg"));
			ByteArrayOutputStream bos11 = new ByteArrayOutputStream();
			ImageIO.write(bImage11, "jpg", bos11);
			byte[] img11 = bos11.toByteArray();

			Product p11 = new Product("HILL'S SCIENCE DIET CANINE ADULT 15kg", (float) 135.00, 12, ProductType.FOOD,
					img11, u2);
			prepo.save(p11);

			BufferedImage bImage12 = ImageIO.read(new File("src/main/resources/static/images/product12.jpg"));
			ByteArrayOutputStream bos12 = new ByteArrayOutputStream();
			ImageIO.write(bImage12, "jpg", bos12);
			byte[] img12 = bos12.toByteArray();

			Product p12 = new Product("VETS ALL NATURAL COMPLETE LIFE KANGAROO & VEGETABLE FOR DOG 5kg", (float) 89.00,
					45, ProductType.FOOD, img12, u2);
			prepo.save(p12);

			BufferedImage bImage13 = ImageIO.read(new File("src/main/resources/static/images/product5.jpg"));
			ByteArrayOutputStream bos13 = new ByteArrayOutputStream();
			ImageIO.write(bImage13, "jpg", bos13);
			byte[] img13 = bos13.toByteArray();

			Product p13 = new Product("BURP LAMB WITH SALMON HYPOALLERGENIC FOR ADULT DOG 1.35kg", (float) 16.20, 13,
					ProductType.FOOD, img13, u2);
			prepo.save(p13);

			BufferedImage bImage14 = ImageIO.read(new File("src/main/resources/static/images/product14.jpg"));
			ByteArrayOutputStream bos14 = new ByteArrayOutputStream();
			ImageIO.write(bImage14, "jpg", bos14);
			byte[] img14 = bos14.toByteArray();

			Product p14 = new Product("WELLNESS CORE OCEAN 22lbs", (float) 152.45, 28, ProductType.FOOD, img14, u1);
			prepo.save(p14);

			BufferedImage bImage15 = ImageIO.read(new File("src/main/resources/static/images/product2.jpg"));
			ByteArrayOutputStream bos15 = new ByteArrayOutputStream();
			ImageIO.write(bImage15, "jpg", bos15);
			byte[] img15 = bos15.toByteArray();

			Product p15 = new Product("FISH 4 DOGS FINEST SALMON COMPLETE (SMALL BITE) 12kg", (float) 172.00, 37,
					ProductType.FOOD, img15, u1);
			prepo.save(p15);

			BufferedImage bImage16 = ImageIO.read(new File("src/main/resources/static/images/product16.jpg"));
			ByteArrayOutputStream bos16 = new ByteArrayOutputStream();
			ImageIO.write(bImage16, "jpg", bos16);
			byte[] img16 = bos16.toByteArray();

			Product p16 = new Product("WELLNESS COMPLETE HEALTH WHITEFISH & SWEET POTATO 30lbs", (float) 139.60, 26,
					ProductType.FOOD, img16, u1);
			prepo.save(p16);

			BufferedImage bImage17 = ImageIO.read(new File("src/main/resources/static/images/product17.jpg"));
			ByteArrayOutputStream bos17 = new ByteArrayOutputStream();
			ImageIO.write(bImage17, "jpg", bos17);
			byte[] img17 = bos17.toByteArray();

			Product p17 = new Product("EUKANUBA ADULT LAMB & RICE (LARGE BREED) 15kg", (float) 111.30, 3,
					ProductType.FOOD, img17, u1);
			prepo.save(p17);

			BufferedImage bImage18 = ImageIO.read(new File("src/main/resources/static/images/product18.jpg"));
			ByteArrayOutputStream bos18 = new ByteArrayOutputStream();
			ImageIO.write(bImage18, "jpg", bos18);
			byte[] img18 = bos18.toByteArray();

			Product p18 = new Product("ADDICTION ZEN VEGETARIAN 20lbs", (float) 68.00, 3, ProductType.FOOD, img18, u1);
			prepo.save(p18);

			BufferedImage bImage19 = ImageIO.read(new File("src/main/resources/static/images/product19.jpg"));
			ByteArrayOutputStream bos19 = new ByteArrayOutputStream();
			ImageIO.write(bImage19, "jpg", bos19);
			byte[] img19 = bos19.toByteArray();

			Product p19 = new Product("ROYAL CANIN PUPPY (MEDIUM BREED) 10kg", (float) 96.30, 16, ProductType.FOOD,
					img19, u1);
			prepo.save(p19);

			BufferedImage bImage20 = ImageIO.read(new File("src/main/resources/static/images/product20.jpg"));
			ByteArrayOutputStream bos20 = new ByteArrayOutputStream();
			ImageIO.write(bImage20, "jpg", bos20);
			byte[] img20 = bos20.toByteArray();

			Product p20 = new Product("DOG IT DURABLE BOWL w SS INSERT - WHITE MEDIUM", (float) 26.60, 16,
					ProductType.ACCESSORIES, img20, u1);
			prepo.save(p20);

			BufferedImage bImage21 = ImageIO.read(new File("src/main/resources/static/images/product21.jpg"));
			ByteArrayOutputStream bos21 = new ByteArrayOutputStream();
			ImageIO.write(bImage21, "jpg", bos21);
			byte[] img21 = bos21.toByteArray();

			Product p21 = new Product("TRUSTIE ELEVATED SLANTED BOWL (OCEAN GREY) (300ml) (17.5x4.5cm)", (float) 11.00,
					22, ProductType.ACCESSORIES, img21, u1);
			prepo.save(p21);

			BufferedImage bImage22 = ImageIO.read(new File("src/main/resources/static/images/product22.jpg"));
			ByteArrayOutputStream bos22 = new ByteArrayOutputStream();
			ImageIO.write(bImage22, "jpg", bos22);
			byte[] img22 = bos22.toByteArray();

			Product p22 = new Product("FLEXI RETRACTABLE LEASH - VARIO CORD SMALL 5m - BLUE", (float) 14.80, 37,
					ProductType.ACCESSORIES, img22, u1);
			prepo.save(p22);

			BufferedImage bImage23 = ImageIO.read(new File("src/main/resources/static/images/product23.jpg"));
			ByteArrayOutputStream bos23 = new ByteArrayOutputStream();
			ImageIO.write(bImage23, "jpg", bos23);
			byte[] img23 = bos23.toByteArray();

			Product p23 = new Product("FOUR PAWS DOG TRAINING LEAD 10ft", (float) 22.45, 1, ProductType.ACCESSORIES,
					img23, u1);
			prepo.save(p23);

			BufferedImage bImage24 = ImageIO.read(new File("src/main/resources/static/images/product24.jpg"));
			ByteArrayOutputStream bos24 = new ByteArrayOutputStream();
			ImageIO.write(bImage24, "jpg", bos24);
			byte[] img24 = bos24.toByteArray();

			Product p24 = new Product("NOVAGUARD WOUND GUARD PROTECTION FOR SMALL / MEDIUM DOGS (10-25kg)",
					(float) 20.20, 2, ProductType.CARE, img24, u1);
			prepo.save(p24);

			BufferedImage bImage25 = ImageIO.read(new File("src/main/resources/static/images/product25.jpg"));
			ByteArrayOutputStream bos25 = new ByteArrayOutputStream();
			ImageIO.write(bImage25, "jpg", bos25);
			byte[] img25 = bos25.toByteArray();

			Product p25 = new Product("FRONTLINE FLEA & TICK PLUS 6s FOR MEDIUM DOG 10-20kg", (float) 72.85, 13,
					ProductType.CARE, img25, u2);
			prepo.save(p25);

			BufferedImage bImage26 = ImageIO.read(new File("src/main/resources/static/images/product26.jpg"));
			ByteArrayOutputStream bos26 = new ByteArrayOutputStream();
			ImageIO.write(bImage26, "jpg", bos26);
			byte[] img26 = bos26.toByteArray();

			Product p26 = new Product("NEXGARD FLEAS & TICKS FLAV CHEW (M) DOG (4-10kg) 6 Tabs", (float) 70.95, 14,
					ProductType.CARE, img26, u2);
			prepo.save(p26);

			BufferedImage bImage27 = ImageIO.read(new File("src/main/resources/static/images/product27.jpg"));
			ByteArrayOutputStream bos27 = new ByteArrayOutputStream();
			ImageIO.write(bImage27, "jpg", bos27);
			byte[] img27 = bos27.toByteArray();

			Product p27 = new Product("YOURS DROOLLY FLEA & TICK SHAMPOO & CONDITIONER 500ml", (float) 23.50, 9,
					ProductType.CARE, img27, u2);
			prepo.save(p27);

			BufferedImage bImage28 = ImageIO.read(new File("src/main/resources/static/images/product28.jpg"));
			ByteArrayOutputStream bos28 = new ByteArrayOutputStream();
			ImageIO.write(bImage28, "jpg", bos28);
			byte[] img28 = bos28.toByteArray();

			Product p28 = new Product("ESPREE FLEA & TICK DOG SPRAY 355ml", (float) 25.00, 8, ProductType.CARE, img28,
					u2);
			prepo.save(p28);

			BufferedImage bImage29 = ImageIO.read(new File("src/main/resources/static/images/product29.jpg"));
			ByteArrayOutputStream bos29 = new ByteArrayOutputStream();
			ImageIO.write(bImage29, "jpg", bos29);
			byte[] img29 = bos29.toByteArray();

			Product p29 = new Product("NYLABONE POWER CHEW TEXTURE BEEF JERKY - WOLF", (float) 16.30, 45,
					ProductType.TOY, img29, u2);
			prepo.save(p29);

			BufferedImage bImage30 = ImageIO.read(new File("src/main/resources/static/images/product30.jpg"));
			ByteArrayOutputStream bos30 = new ByteArrayOutputStream();
			ImageIO.write(bImage30, "jpg", bos30);
			byte[] img30 = bos30.toByteArray();

			Product p30 = new Product("KONG AIR KONG SQUEAKER DUMBBELL - MEDIUM", (float) 28.85, 37, ProductType.TOY,
					img30, u2);
			prepo.save(p30);

			BufferedImage bImage31 = ImageIO.read(new File("src/main/resources/static/images/product31.jpg"));
			ByteArrayOutputStream bos31 = new ByteArrayOutputStream();
			ImageIO.write(bImage31, "jpg", bos31);
			byte[] img31 = bos31.toByteArray();

			Product p31 = new Product("NYLABONE DURA CHEW WISHBONE - REGULAR", (float) 15.80, 40, ProductType.TOY,
					img31, u2);
			prepo.save(p31);

			BufferedImage bImage32 = ImageIO.read(new File("src/main/resources/static/images/product32.jpg"));
			ByteArrayOutputStream bos32 = new ByteArrayOutputStream();
			ImageIO.write(bImage32, "jpg", bos32);
			byte[] img32 = bos32.toByteArray();

			Product p32 = new Product("KONG WUBBA FRIEND LARGE", (float) 23.50, 8, ProductType.TOY, img32, u2);
			prepo.save(p32);

			BufferedImage bImage33 = ImageIO.read(new File("src/main/resources/static/images/product33.jpg"));
			ByteArrayOutputStream bos33 = new ByteArrayOutputStream();
			ImageIO.write(bImage33, "jpg", bos33);
			byte[] img33 = bos33.toByteArray();

			Product p33 = new Product("NYLABONE FLEXIBLE DENTAL CHEW - WOLF", (float) 19.50, 1, ProductType.TOY, img33,
					u2);
			prepo.save(p33);

			BufferedImage bImage34 = ImageIO.read(new File("src/main/resources/static/images/product34.png"));
			ByteArrayOutputStream bos34 = new ByteArrayOutputStream();
			ImageIO.write(bImage34, "png", bos34);
			byte[] img34 = bos34.toByteArray();

			Product p34 = new Product("Sable Shetland Sheepdog - puppy, 2 months; Parents are award-winning show dogs.",
					(float) 1200.00, 1, ProductType.DOG, img34, u2);
			prepo.save(p34);

			BufferedImage bImage35 = ImageIO.read(new File("src/main/resources/static/images/product35.jpg"));
			ByteArrayOutputStream bos35 = new ByteArrayOutputStream();
			ImageIO.write(bImage35, "jpg", bos35);
			byte[] img35 = bos35.toByteArray();

			Product p35 = new Product("Appenzeller Sennenhund - puppy, 3 months. Bred in the Swiss alps.",
					(float) 2800.00, 1, ProductType.DOG, img35, u2);
			prepo.save(p35);

			BufferedImage bImage36 = ImageIO.read(new File("src/main/resources/static/images/product36.jpg"));
			ByteArrayOutputStream bos36 = new ByteArrayOutputStream();
			ImageIO.write(bImage36, "jpg", bos36);
			byte[] img36 = bos36.toByteArray();

			Product p36 = new Product("Italian Greyhound - puppies, 2 months. Parents are champion race-dogs.",
					(float) 1400.00, 1, ProductType.DOG, img36, u2);
			prepo.save(p36);

//			----- Service providers ------

			BufferedImage servpImage = ImageIO.read(new File("src/main/resources/static/images/servp.jpg"));
			ByteArrayOutputStream servpbos = new ByteArrayOutputStream();
			ImageIO.write(servpImage, "jpg", servpbos);
			byte[] servp = servpbos.toByteArray();

			Provider sp = new Provider("Dog Care Consultation",
					"First time dog owner? Thinking of getting a particular breed? We have extensive experiential knowledge to help you with your concerns, whether it is on the health, nutritional and grooming needs, trainability or adaptability. We also advise on barking tendencies, apartment friendliness, child friendliness and hypoallergenic concerns on popular breeds. Come down to our store for hands-on advice today!",
					(float) 30.00, servp);
			prrepo.save(sp);

			BufferedImage servpImage2 = ImageIO.read(new File("src/main/resources/static/images/servp2.jpg"));
			ByteArrayOutputStream servpbos2 = new ByteArrayOutputStream();
			ImageIO.write(servpImage2, "jpg", servpbos2);
			byte[] servp2 = servpbos2.toByteArray();

			Provider sp2 = new Provider("Dog Grooming - Basic Package",
					"Grooming is essential in maintaining the health, wellbeing and happiness of your dog! This services includes nail clipping, ear cleaning, shaving of anal area, between the legs, belly and paw pads. Bathing, conditioning, drying and brushing. Trimming fur off the nose bridge and around paw (if applicable). Prices start at $40++ for small dogs (~1kg), with additional $5 for every kg.",
					(float) 40.00, servp2);
			prrepo.save(sp2);

			BufferedImage servpImage3 = ImageIO.read(new File("src/main/resources/static/images/servp3.jpg"));
			ByteArrayOutputStream servpbos3 = new ByteArrayOutputStream();
			ImageIO.write(servpImage3, "jpg", servpbos3);
			byte[] servp3 = servpbos3.toByteArray();

			Provider sp3 = new Provider("Dog Grooming - Full Package",
					"Full grooming comprises of everything in the basic package, with additional hair styling/cutting. Prices start at $50++ for small dogs (~1kg), with additional $5 for every kg.",
					(float) 50.00, servp3);
			prrepo.save(sp3);

			BufferedImage servpImage4 = ImageIO.read(new File("src/main/resources/static/images/servp4.jpg"));
			ByteArrayOutputStream servpbos4 = new ByteArrayOutputStream();
			ImageIO.write(servpImage4, "jpg", servpbos4);
			byte[] servp4 = servpbos4.toByteArray();

			Provider sp4 = new Provider("Pet Spa",
					"Pamper your furkids like yourself at our award-winning facility, accredited by the Singapore Kennel Club. Our team of professional groomers tailor each service based on the needs of your pet. A day at the spa includes bathing, grooming, styling and colouring, and a spa service. Choose between skin clinic chlorhexidine treatment, Japanese Herb Treatment, nano-microbubble spa or CO2 treatment. Prices for small dogs start from $80++",
					(float) 80.00, servp4);
			prrepo.save(sp4);

			BufferedImage servpImage5 = ImageIO.read(new File("src/main/resources/static/images/servp5.jpg"));
			ByteArrayOutputStream servpbos5 = new ByteArrayOutputStream();
			ImageIO.write(servpImage5, "jpg", servpbos5);
			byte[] servp5 = servpbos5.toByteArray();

			Provider sp5 = new Provider("De-Ticking",
					"Have a bad tick infestation? Leave it to the professionals to to de-tick. Prices start at $30++. Additional charges may apply depending on the severity of infestation. ",
					(float) 30.00, servp5);
			prrepo.save(sp5);

			BufferedImage servpImage6 = ImageIO.read(new File("src/main/resources/static/images/servp6.jpg"));
			ByteArrayOutputStream servpbos6 = new ByteArrayOutputStream();
			ImageIO.write(servpImage6, "jpg", servpbos6);
			byte[] servp6 = servpbos6.toByteArray();

			Provider sp6 = new Provider("Dental Care",
					"Nervous about brushing your dog's teeth? Or perhaps your dog has a case of bad breath. We provide basic tooth brushing and cleaning services to put you at ease.",
					(float) 20.00, servp6);
			prrepo.save(sp6);

			BufferedImage servpImage7 = ImageIO.read(new File("src/main/resources/static/images/servp7.jpg"));
			ByteArrayOutputStream servpbos7 = new ByteArrayOutputStream();
			ImageIO.write(servpImage7, "jpg", servpbos7);
			byte[] servp7 = servpbos7.toByteArray();

			Provider sp7 = new Provider("Pet Day Care",
					"We provide pet day-boarding services to keep you furry companions company while you're away. Includes 2 meals, 4 walks a day and a complimentary webcam viewing for you to check up on them. Complimentary bath and blowdry for stays of more than 3 consecutive days. Check-in: 8am. Check-out: 6pm.",
					(float) 80.00, servp7);
			prrepo.save(sp7);

			BufferedImage servpImage8 = ImageIO.read(new File("src/main/resources/static/images/servp8.jpg"));
			ByteArrayOutputStream servpbos8 = new ByteArrayOutputStream();
			ImageIO.write(servpImage8, "jpg", servpbos8);
			byte[] servp8 = servpbos8.toByteArray();

			Provider sp8 = new Provider("Pet Hotel",
					"Going away for a vacation and can't bring your loved one(s) along? Fret not! We offer overnight services. Full board services start from $100 per day (12pm-12pm). Additional charges may apply depending on choice of suite, meal types and dog size.",
					(float) 100.00, servp8);
			prrepo.save(sp8);

			BufferedImage servpImage9 = ImageIO.read(new File("src/main/resources/static/images/servp9.jpg"));
			ByteArrayOutputStream servpbos9 = new ByteArrayOutputStream();
			ImageIO.write(servpImage9, "jpg", servpbos9);
			byte[] servp9 = servpbos9.toByteArray();

			Provider sp9 = new Provider("Basic Dog Training",
					"Spend quality time with you dog and get to know it better through our obedience course. We'll enable you to better understand and build a stronger connection between the two of you. Improving trust and breeding a loving relationship will give both of you greater satisfaction. The training will be conducted in groups over 10 weeks.",
					(float) 800.00, servp9);
			prrepo.save(sp9);

			BufferedImage servpImage10 = ImageIO.read(new File("src/main/resources/static/images/servp10.jpg"));
			ByteArrayOutputStream servpbos10 = new ByteArrayOutputStream();
			ImageIO.write(servpImage10, "jpg", servpbos10);
			byte[] servp10 = servpbos10.toByteArray();

			Provider sp10 = new Provider("Potty Training",
					"Toilet train your dog or puppy through our course. It is a small price to pay compared to having clean up that soiled carpet or mat many times over. The course is conducted in groups over 10 weeks.",
					(float) 480.00, servp10);
			prrepo.save(sp10);
			
			
//			----- Posts ------
			
			BufferedImage ptImage = ImageIO.read(new File("src/main/resources/static/images/ptimg.jpg"));
			ByteArrayOutputStream ptbos = new ByteArrayOutputStream();
			ImageIO.write(ptImage, "jpg", ptbos);
			byte[] ptimg = ptbos.toByteArray();

			Post pt = new Post(PostType.LOST, "Lost poodle :(", "Last seen on 14 July at 7pm along Sycamore Canyon rd. $5000 reward!!!", ptimg, u3);
			ptrepo.save(pt);
			
			BufferedImage ptImage2 = ImageIO.read(new File("src/main/resources/static/images/ptimg2.jpg"));
			ByteArrayOutputStream ptbos2 = new ByteArrayOutputStream();
			ImageIO.write(ptImage2, "jpg", ptbos2);
			byte[] ptimg2 = ptbos2.toByteArray();

			Post pt2 = new Post(PostType.LOST, "Lost Sheltie", "Prince was last seen on 23 Dec at Platinum Dogs club. Offering $2000 reward.", ptimg2, u4);
			ptrepo.save(pt2);
			
			BufferedImage ptImage3 = ImageIO.read(new File("src/main/resources/static/images/ptimg3.jpg"));
			ByteArrayOutputStream ptbos3 = new ByteArrayOutputStream();
			ImageIO.write(ptImage3, "jpg", ptbos3);
			byte[] ptimg3 = ptbos3.toByteArray();

			Post pt3 = new Post(PostType.FOUND, "FOUND: Terrier mix", "Found this gray-white-gold terrier mix outside on the porch. Please call the number if it's yours. And provide some proof of ownership.", ptimg3, u5);

			ptrepo.save(pt3);
			
			
//			----- Date and Time objects ------
			
			ArrayList<String> timeSlots = new ArrayList<>();
			timeSlots.add("8am");
			timeSlots.add("10am");
			timeSlots.add("1pm");
			timeSlots.add("3pm");
			timeSlots.add("5pm");
			
			LocalDate jan1 = LocalDate.of(2021, 1, 1);
			LocalDate jan2 = LocalDate.of(2021, 1, 2);
			LocalDate jan3 = LocalDate.of(2021, 1, 3);
			LocalDate jan4 = LocalDate.of(2021, 1, 4);
			LocalDate jan5 = LocalDate.of(2021, 1, 5);
			LocalDate jan6 = LocalDate.of(2021, 1, 6);
			LocalDate jan7 = LocalDate.of(2021, 1, 7);
			LocalDate jan8 = LocalDate.of(2021, 1, 8);
			LocalDate jan9 = LocalDate.of(2021, 1, 9);
			LocalDate jan10 = LocalDate.of(2021, 1, 10);
			LocalDate jan11 = LocalDate.of(2021, 1, 11);
			LocalDate jan12 = LocalDate.of(2021, 1, 12);
			LocalDate jan13 = LocalDate.of(2021, 1, 13);
			LocalDate jan14 = LocalDate.of(2021, 1, 14);
			LocalDate jan15 = LocalDate.of(2021, 1, 15);
			LocalDate jan16 = LocalDate.of(2021, 1, 16);
			LocalDate jan17 = LocalDate.of(2021, 1, 17);
			LocalDate jan18 = LocalDate.of(2021, 1, 18);
			LocalDate jan19 = LocalDate.of(2021, 1, 19);
			LocalDate jan20 = LocalDate.of(2021, 1, 20);
			LocalDate jan21 = LocalDate.of(2021, 1, 21);
			LocalDate jan22 = LocalDate.of(2021, 1, 22);
			LocalDate jan23 = LocalDate.of(2021, 1, 23);
			LocalDate jan24 = LocalDate.of(2021, 1, 24);
			LocalDate jan25 = LocalDate.of(2021, 1, 25);
			LocalDate jan26 = LocalDate.of(2021, 1, 26);
			LocalDate jan27 = LocalDate.of(2021, 1, 27);
			LocalDate jan28 = LocalDate.of(2021, 1, 28);
			LocalDate jan29 = LocalDate.of(2021, 1, 29);
			LocalDate jan30 = LocalDate.of(2021, 1, 30);
			LocalDate jan31 = LocalDate.of(2021, 1, 31);
			
			
			LocalDate feb1 = LocalDate.of(2021, 2, 1);
			LocalDate feb2 = LocalDate.of(2021, 2, 2);
			LocalDate feb3 = LocalDate.of(2021, 2, 3);
			LocalDate feb4 = LocalDate.of(2021, 2, 4);
			LocalDate feb5 = LocalDate.of(2021, 2, 5);
			LocalDate feb6 = LocalDate.of(2021, 2, 6);
			LocalDate feb7 = LocalDate.of(2021, 2, 7);
			LocalDate feb8 = LocalDate.of(2021, 2, 8);
			LocalDate feb9 = LocalDate.of(2021, 2, 9);
			LocalDate feb10 = LocalDate.of(2021, 2, 10);
			LocalDate feb11 = LocalDate.of(2021, 2, 11);
			LocalDate feb12 = LocalDate.of(2021, 2, 12);
			LocalDate feb13 = LocalDate.of(2021, 2, 13);
			LocalDate feb14 = LocalDate.of(2021, 2, 14);
			LocalDate feb15 = LocalDate.of(2021, 2, 15);
			LocalDate feb16 = LocalDate.of(2021, 2, 16);
			LocalDate feb17 = LocalDate.of(2021, 2, 17);
			LocalDate feb18 = LocalDate.of(2021, 2, 18);
			LocalDate feb19 = LocalDate.of(2021, 2, 19);
			LocalDate feb20 = LocalDate.of(2021, 2, 20);
			LocalDate feb21 = LocalDate.of(2021, 2, 21);
			LocalDate feb22 = LocalDate.of(2021, 2, 22);
			LocalDate feb23 = LocalDate.of(2021, 2, 23);
			LocalDate feb24 = LocalDate.of(2021, 2, 24);
			LocalDate feb25 = LocalDate.of(2021, 2, 25);
			LocalDate feb26 = LocalDate.of(2021, 2, 26);
			LocalDate feb27 = LocalDate.of(2021, 2, 27);
			LocalDate feb28 = LocalDate.of(2021, 2, 28);
			
			
//			----- Service - sp2, Basic Grooming ------
			
			Services sp2s1 = new Services(timeSlots, sp2, jan1);
			srepo.save(sp2s1);
			Services sp2s2 = new Services(timeSlots, sp2, jan2);
			srepo.save(sp2s2);
			Services sp2s3 = new Services(timeSlots, sp2, jan3);
			srepo.save(sp2s3);
			Services sp2s4 = new Services(timeSlots, sp2, jan4);
			srepo.save(sp2s4);
			Services sp2s5 = new Services(timeSlots, sp2, jan5);
			srepo.save(sp2s5);
			Services sp2s6 = new Services(timeSlots, sp2, jan6);
			srepo.save(sp2s6);
			Services sp2s7 = new Services(timeSlots, sp2, jan7);
			srepo.save(sp2s7);
			Services sp2s8 = new Services(timeSlots, sp2, jan8);
			srepo.save(sp2s8);
			Services sp2s9 = new Services(timeSlots, sp2, jan9);
			srepo.save(sp2s9);
			Services sp2s10 = new Services(timeSlots, sp2, jan10);
			srepo.save(sp2s10);
			Services sp2s11 = new Services(timeSlots, sp2, jan11);
			srepo.save(sp2s11);
			Services sp2s12 = new Services(timeSlots, sp2, jan12);
			srepo.save(sp2s12);
			Services sp2s13 = new Services(timeSlots, sp2, jan13);
			srepo.save(sp2s13);
			Services sp2s14 = new Services(timeSlots, sp2, jan14);
			srepo.save(sp2s14);
			Services sp2s15 = new Services(timeSlots, sp2, jan15);
			srepo.save(sp2s15);
			Services sp2s16 = new Services(timeSlots, sp2, jan16);
			srepo.save(sp2s16);
			Services sp2s17 = new Services(timeSlots, sp2, jan17);
			srepo.save(sp2s17);
			Services sp2s18 = new Services(timeSlots, sp2, jan18);
			srepo.save(sp2s18);
			Services sp2s19 = new Services(timeSlots, sp2, jan19);
			srepo.save(sp2s19);
			Services sp2s20 = new Services(timeSlots, sp2, jan20);
			srepo.save(sp2s20);
			Services sp2s21 = new Services(timeSlots, sp2, jan21);
			srepo.save(sp2s21);
			Services sp2s22 = new Services(timeSlots, sp2, jan22);
			srepo.save(sp2s22);
			Services sp2s23 = new Services(timeSlots, sp2, jan23);
			srepo.save(sp2s23);
			Services sp2s24 = new Services(timeSlots, sp2, jan24);
			srepo.save(sp2s24);
			Services sp2s25 = new Services(timeSlots, sp2, jan25);
			srepo.save(sp2s25);
			Services sp2s26 = new Services(timeSlots, sp2, jan26);
			srepo.save(sp2s26);
			Services sp2s27 = new Services(timeSlots, sp2, jan27);
			srepo.save(sp2s27);
			Services sp2s28 = new Services(timeSlots, sp2, jan28);
			srepo.save(sp2s28);
			Services sp2s29 = new Services(timeSlots, sp2, jan29);
			srepo.save(sp2s29);
			Services sp2s30 = new Services(timeSlots, sp2, jan30);
			srepo.save(sp2s30);
			Services sp2s31 = new Services(timeSlots, sp2, jan31);
			srepo.save(sp2s31);
			
			
			Services sp2s1_2 = new Services(timeSlots, sp2, feb1);
			srepo.save(sp2s1_2);
			Services sp2s2_2 = new Services(timeSlots, sp2, feb2);
			srepo.save(sp2s2_2);
			Services sp2s3_2 = new Services(timeSlots, sp2, feb3);
			srepo.save(sp2s3_2);
			Services sp2s4_2 = new Services(timeSlots, sp2, feb4);
			srepo.save(sp2s4_2);
			Services sp2s5_2 = new Services(timeSlots, sp2, feb5);
			srepo.save(sp2s5_2);
			Services sp2s6_2 = new Services(timeSlots, sp2, feb6);
			srepo.save(sp2s6_2);
			Services sp2s7_2 = new Services(timeSlots, sp2, feb7);
			srepo.save(sp2s7_2);
			Services sp2s8_2 = new Services(timeSlots, sp2, feb8);
			srepo.save(sp2s8_2);
			Services sp2s9_2 = new Services(timeSlots, sp2, feb9);
			srepo.save(sp2s9_2);
			Services sp2s10_2 = new Services(timeSlots, sp2, feb10);
			srepo.save(sp2s10_2);
			Services sp2s11_2 = new Services(timeSlots, sp2, feb11);
			srepo.save(sp2s11_2);
			Services sp2s12_2 = new Services(timeSlots, sp2, feb12);
			srepo.save(sp2s12_2);
			Services sp2s13_2 = new Services(timeSlots, sp2, feb13);
			srepo.save(sp2s13_2);
			Services sp2s14_2 = new Services(timeSlots, sp2, feb14);
			srepo.save(sp2s14_2);
			Services sp2s15_2 = new Services(timeSlots, sp2, feb15);
			srepo.save(sp2s15_2);
			Services sp2s16_2 = new Services(timeSlots, sp2, feb16);
			srepo.save(sp2s16_2);
			Services sp2s17_2 = new Services(timeSlots, sp2, feb17);
			srepo.save(sp2s17_2);
			Services sp2s18_2 = new Services(timeSlots, sp2, feb18);
			srepo.save(sp2s18_2);
			Services sp2s19_2 = new Services(timeSlots, sp2, feb19);
			srepo.save(sp2s19_2);
			Services sp2s20_2 = new Services(timeSlots, sp2, feb20);
			srepo.save(sp2s20_2);
			Services sp2s21_2 = new Services(timeSlots, sp2, feb21);
			srepo.save(sp2s21_2);
			Services sp2s22_2 = new Services(timeSlots, sp2, feb22);
			srepo.save(sp2s22_2);
			Services sp2s23_2 = new Services(timeSlots, sp2, feb23);
			srepo.save(sp2s23_2);
			Services sp2s24_2 = new Services(timeSlots, sp2, feb24);
			srepo.save(sp2s24_2);
			Services sp2s25_2 = new Services(timeSlots, sp2, feb25);
			srepo.save(sp2s25_2);
			Services sp2s26_2 = new Services(timeSlots, sp2, feb26);
			srepo.save(sp2s26_2);
			Services sp2s27_2 = new Services(timeSlots, sp2, feb27);
			srepo.save(sp2s27_2);
			Services sp2s28_2 = new Services(timeSlots, sp2, feb28);
			srepo.save(sp2s28_2);
			
			
//			----- Reservation - sp2, Basic grooming------
//			-------- January --------
			
			Reservation ressp2s1_a = new Reservation(sp2s1, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s1_a);
			Reservation ressp2s1_b = new Reservation(sp2s1, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s1_b);
			Reservation ressp2s1_c = new Reservation(sp2s1, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s1_c);
			Reservation ressp2s1_d = new Reservation(sp2s1, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s1_d);
			Reservation ressp2s1_e = new Reservation(sp2s1, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s1_e);
			

			Reservation ressp2s2_a = new Reservation(sp2s2, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s2_a);
			Reservation ressp2s2_b = new Reservation(sp2s2, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s2_b);
			Reservation ressp2s2_c = new Reservation(sp2s2, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s2_c);
			Reservation ressp2s2_d = new Reservation(sp2s2, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s2_d);
			Reservation ressp2s2_e = new Reservation(sp2s2, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s2_e);
			
			
			Reservation ressp2s3_a = new Reservation(sp2s3, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s3_a);
			Reservation ressp2s3_b = new Reservation(sp2s3, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s3_b);
			Reservation ressp2s3_c = new Reservation(sp2s3, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s3_c);
			Reservation ressp2s3_d = new Reservation(sp2s3, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s3_d);
			Reservation ressp2s3_e = new Reservation(sp2s3, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s3_e);	
			
			Reservation ressp2s4_a = new Reservation(sp2s4, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s4_a);
			Reservation ressp2s4_b = new Reservation(sp2s4, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s4_b);
			Reservation ressp2s4_c = new Reservation(sp2s4, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s4_c);
			Reservation ressp2s4_d = new Reservation(sp2s4, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s4_d);
			Reservation ressp2s4_e = new Reservation(sp2s4, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s4_e);
			
			Reservation ressp2s5_a = new Reservation(sp2s5, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s5_a);
			Reservation ressp2s5_c = new Reservation(sp2s5, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s5_c);
			Reservation ressp2s5_e = new Reservation(sp2s5, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s5_e);
			
			Reservation ressp2s6_a = new Reservation(sp2s6, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s6_a);
			Reservation ressp2s6_c = new Reservation(sp2s6, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s6_c);
			Reservation ressp2s6_e = new Reservation(sp2s6, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s6_e);
			
			Reservation ressp2s7_a = new Reservation(sp2s7, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s7_a);
			Reservation ressp2s7_c = new Reservation(sp2s7, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s7_c);
			Reservation ressp2s7_e = new Reservation(sp2s7, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s7_e);

			Reservation ressp2s8_a = new Reservation(sp2s8, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s8_a);
			Reservation ressp2s8_b = new Reservation(sp2s8, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s8_b);
			Reservation ressp2s8_c = new Reservation(sp2s8, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s8_c);
			Reservation ressp2s8_d = new Reservation(sp2s8, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s8_d);
			Reservation ressp2s8_e = new Reservation(sp2s8, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s8_e);

			Reservation ressp2s9_a = new Reservation(sp2s9, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s9_a);
			Reservation ressp2s9_b = new Reservation(sp2s9, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s9_b);
			Reservation ressp2s9_c = new Reservation(sp2s9, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s9_c);
			Reservation ressp2s9_d = new Reservation(sp2s9, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s9_d);
			Reservation ressp2s9_e = new Reservation(sp2s9, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s9_e);

			Reservation ressp2s10_a = new Reservation(sp2s10, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s10_a);
			Reservation ressp2s10_b = new Reservation(sp2s10, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s10_b);
			Reservation ressp2s10_c = new Reservation(sp2s10, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s10_c);
			Reservation ressp2s10_d = new Reservation(sp2s10, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s10_d);
			Reservation ressp2s10_e = new Reservation(sp2s10, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s10_e);

			Reservation ressp2s11_a = new Reservation(sp2s11, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s11_a);
			Reservation ressp2s11_c = new Reservation(sp2s11, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s11_c);
			Reservation ressp2s11_e = new Reservation(sp2s11, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s11_e);


			Reservation ressp2s12_b = new Reservation(sp2s12, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s12_b);
			Reservation ressp2s12_d = new Reservation(sp2s12, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s12_d);

			Reservation ressp2s13_a = new Reservation(sp2s13, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s13_a);
			Reservation ressp2s13_b = new Reservation(sp2s13, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s13_b);
			Reservation ressp2s13_c = new Reservation(sp2s13, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s13_c);

			Reservation ressp2s14_c = new Reservation(sp2s14, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s14_c);
			Reservation ressp2s14_d = new Reservation(sp2s14, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s14_d);
			Reservation ressp2s14_e = new Reservation(sp2s14, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s14_e);

			Reservation ressp2s15_c = new Reservation(sp2s15, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s15_c);
			Reservation ressp2s15_d = new Reservation(sp2s15, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s15_d);
			Reservation ressp2s15_e = new Reservation(sp2s15, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s15_e);

			Reservation ressp2s16_a = new Reservation(sp2s16, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s16_a);
			Reservation ressp2s16_b = new Reservation(sp2s16, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s16_b);
			Reservation ressp2s16_c = new Reservation(sp2s16, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s16_c);
			Reservation ressp2s16_d = new Reservation(sp2s16, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s16_d);
			Reservation ressp2s16_e = new Reservation(sp2s16, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s16_e);

			Reservation ressp2s17_a = new Reservation(sp2s17, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s17_a);
			Reservation ressp2s17_b = new Reservation(sp2s17, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s17_b);
			Reservation ressp2s17_c = new Reservation(sp2s17, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s17_c);
			Reservation ressp2s17_d = new Reservation(sp2s17, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s17_d);
			Reservation ressp2s17_e = new Reservation(sp2s17, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s17_e);

			Reservation ressp2s18_a = new Reservation(sp2s18, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s18_a);
			Reservation ressp2s18_b = new Reservation(sp2s18, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s18_b);
			Reservation ressp2s18_e = new Reservation(sp2s18, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s18_e);

			Reservation ressp2s19_a = new Reservation(sp2s19, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s19_a);
			Reservation ressp2s19_c = new Reservation(sp2s19, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s19_c);
			Reservation ressp2s19_d = new Reservation(sp2s19, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s19_d);

			Reservation ressp2s20_c = new Reservation(sp2s20, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s20_c);
			Reservation ressp2s20_d = new Reservation(sp2s20, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s20_d);
			Reservation ressp2s20_e = new Reservation(sp2s20, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s20_e);

			Reservation ressp2s21_a = new Reservation(sp2s21, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s21_a);
			Reservation ressp2s21_c = new Reservation(sp2s21, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s21_c);
			Reservation ressp2s21_e = new Reservation(sp2s21, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s21_e);

			Reservation ressp2s22_c = new Reservation(sp2s22, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s22_c);
			Reservation ressp2s22_d = new Reservation(sp2s22, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s22_d);
			Reservation ressp2s22_e = new Reservation(sp2s22, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s22_e);

			Reservation ressp2s23_a = new Reservation(sp2s23, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s23_a);
			Reservation ressp2s23_b = new Reservation(sp2s23, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s23_b);
			Reservation ressp2s23_c = new Reservation(sp2s23, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s23_c);
			Reservation ressp2s23_d = new Reservation(sp2s23, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s23_d);
			Reservation ressp2s23_e = new Reservation(sp2s23, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s23_e);

			Reservation ressp2s24_a = new Reservation(sp2s24, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s24_a);
			Reservation ressp2s24_b = new Reservation(sp2s24, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s24_b);
			Reservation ressp2s24_c = new Reservation(sp2s24, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s24_c);
			Reservation ressp2s24_d = new Reservation(sp2s24, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s24_d);
			Reservation ressp2s24_e = new Reservation(sp2s24, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s24_e);

			Reservation ressp2s25_a = new Reservation(sp2s25, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s25_a);
			Reservation ressp2s25_b = new Reservation(sp2s25, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s25_b);
			Reservation ressp2s25_c = new Reservation(sp2s25, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s25_c);

			
			Reservation ressp2s26_c = new Reservation(sp2s26, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s26_c);
			Reservation ressp2s26_d = new Reservation(sp2s26, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s26_d);
			

			Reservation ressp2s27_b = new Reservation(sp2s27, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s27_b);
			Reservation ressp2s27_c = new Reservation(sp2s27, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s27_c);

			Reservation ressp2s28_a = new Reservation(sp2s28, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s28_a);
			Reservation ressp2s28_c = new Reservation(sp2s28, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s28_c);
			Reservation ressp2s28_e = new Reservation(sp2s28, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s28_e);

			
			Reservation ressp2s29_c = new Reservation(sp2s29, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s29_c);
			Reservation ressp2s29_d = new Reservation(sp2s29, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s29_d);
			Reservation ressp2s29_e = new Reservation(sp2s29, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s29_e);

			Reservation ressp2s30_a = new Reservation(sp2s30, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s30_a);
			Reservation ressp2s30_b = new Reservation(sp2s30, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s30_b);
			Reservation ressp2s30_c = new Reservation(sp2s30, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s30_c);
			Reservation ressp2s30_d = new Reservation(sp2s30, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s30_d);
			Reservation ressp2s30_e = new Reservation(sp2s30, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s30_e);

			Reservation ressp2s31_a = new Reservation(sp2s31, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s31_a);
			Reservation ressp2s31_b = new Reservation(sp2s31, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s31_b);
			Reservation ressp2s31_c = new Reservation(sp2s31, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s31_c);
			Reservation ressp2s31_d = new Reservation(sp2s31, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s31_d);
			Reservation ressp2s31_e = new Reservation(sp2s31, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s31_e);

//			-------- February --------
			
			Reservation ressp2s1_a_2 = new Reservation(sp2s1_2, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s1_a_2);
			

			Reservation ressp2s2_b_2 = new Reservation(sp2s2_2, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s2_b_2);
			

			Reservation ressp2s3_c_2 = new Reservation(sp2s3_2, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s3_c_2);


			Reservation ressp2s4_d_2 = new Reservation(sp2s4_2, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s4_d_2);
			

			Reservation ressp2s5_c_2 = new Reservation(sp2s5_2, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s5_c_2);
			Reservation ressp2s5_e_2 = new Reservation(sp2s5_2, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s5_e_2);
			
			Reservation ressp2s6_a_2 = new Reservation(sp2s6_2, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s6_a_2);
			Reservation ressp2s6_b_2 = new Reservation(sp2s6_2, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s6_b_2);
			Reservation ressp2s6_c_2 = new Reservation(sp2s6_2, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s6_c_2);
			Reservation ressp2s6_d_2 = new Reservation(sp2s6_2, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s6_d_2);
			Reservation ressp2s6_e_2 = new Reservation(sp2s6_2, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s6_e_2);
			
			Reservation ressp2s7_a_2 = new Reservation(sp2s7_2, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s7_a_2);
			Reservation ressp2s7_b_2 = new Reservation(sp2s7_2, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s7_b_2);
			Reservation ressp2s7_c_2 = new Reservation(sp2s7_2, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s7_c_2);
			Reservation ressp2s7_d_2 = new Reservation(sp2s7_2, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s7_d_2);
			Reservation ressp2s7_e_2 = new Reservation(sp2s7_2, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s7_e_2);


			Reservation ressp2s8_b_2 = new Reservation(sp2s8_2, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s8_b_2);


			Reservation ressp2s9_e_2 = new Reservation(sp2s9_2, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s9_e_2);

			Reservation ressp2s10_d_2 = new Reservation(sp2s10_2, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s10_d_2);

			Reservation ressp2s11_c_2 = new Reservation(sp2s11_2, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s11_c_2);


			Reservation ressp2s12_b_2 = new Reservation(sp2s12_2, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s12_b_2);
			Reservation ressp2s12_e_2 = new Reservation(sp2s12_2, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s12_e_2);

			Reservation ressp2s13_a_2 = new Reservation(sp2s13_2, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s13_a_2);
			Reservation ressp2s13_b_2 = new Reservation(sp2s13_2, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s13_b_2);
			Reservation ressp2s13_c_2 = new Reservation(sp2s13_2, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s13_c_2);
			Reservation ressp2s13_d_2 = new Reservation(sp2s13_2, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s13_d_2);
			Reservation ressp2s13_e_2 = new Reservation(sp2s13_2, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s13_e_2);

			Reservation ressp2s14_a_2 = new Reservation(sp2s14_2, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp2s14_a_2);
			Reservation ressp2s14_b_2 = new Reservation(sp2s14_2, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s14_b_2);
			Reservation ressp2s14_c_2 = new Reservation(sp2s14_2, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s14_c_2);
			Reservation ressp2s14_d_2 = new Reservation(sp2s14_2, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp2s14_d_2);
			Reservation ressp2s14_e_2 = new Reservation(sp2s14_2, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp2s14_e_2);

			Reservation ressp2s15_c_2 = new Reservation(sp2s15_2, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s15_c_2);

		
			Reservation ressp2s16_c_2 = new Reservation(sp2s16_2, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp2s16_c_2);

			Reservation ressp2s17_b_2 = new Reservation(sp2s17_2, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp2s17_b_2);

		
//			----- Service - sp9, Basic dog training ------
			
			Services sp9s1 = new Services(timeSlots, sp9, jan1);
			srepo.save(sp9s1);
			Services sp9s2 = new Services(timeSlots, sp9, jan2);
			srepo.save(sp9s2);
			Services sp9s3 = new Services(timeSlots, sp9, jan3);
			srepo.save(sp9s3);
			Services sp9s4 = new Services(timeSlots, sp9, jan4);
			srepo.save(sp9s4);
			Services sp9s5 = new Services(timeSlots, sp9, jan5);
			srepo.save(sp9s5);
			Services sp9s6 = new Services(timeSlots, sp9, jan6);
			srepo.save(sp9s6);
			Services sp9s7 = new Services(timeSlots, sp9, jan7);
			srepo.save(sp9s7);
			Services sp9s8 = new Services(timeSlots, sp9, jan8);
			srepo.save(sp9s8);
			Services sp9s9 = new Services(timeSlots, sp9, jan9);
			srepo.save(sp9s9);
			Services sp9s10 = new Services(timeSlots, sp9, jan10);
			srepo.save(sp9s10);
			Services sp9s11 = new Services(timeSlots, sp9, jan11);
			srepo.save(sp9s11);
			Services sp9s12 = new Services(timeSlots, sp9, jan12);
			srepo.save(sp9s12);
			Services sp9s13 = new Services(timeSlots, sp9, jan13);
			srepo.save(sp9s13);
			Services sp9s14 = new Services(timeSlots, sp9, jan14);
			srepo.save(sp9s14);
			Services sp9s15 = new Services(timeSlots, sp9, jan15);
			srepo.save(sp9s15);
			Services sp9s16 = new Services(timeSlots, sp9, jan16);
			srepo.save(sp9s16);
			Services sp9s17 = new Services(timeSlots, sp9, jan17);
			srepo.save(sp9s17);
			Services sp9s18 = new Services(timeSlots, sp9, jan18);
			srepo.save(sp9s18);
			Services sp9s19 = new Services(timeSlots, sp9, jan19);
			srepo.save(sp9s19);
			Services sp9s20 = new Services(timeSlots, sp9, jan20);
			srepo.save(sp9s20);
			Services sp9s21 = new Services(timeSlots, sp9, jan21);
			srepo.save(sp9s21);
			Services sp9s22 = new Services(timeSlots, sp9, jan22);
			srepo.save(sp9s22);
			Services sp9s23 = new Services(timeSlots, sp9, jan23);
			srepo.save(sp9s23);
			Services sp9s24 = new Services(timeSlots, sp9, jan24);
			srepo.save(sp9s24);
			Services sp9s25 = new Services(timeSlots, sp9, jan25);
			srepo.save(sp9s25);
			Services sp9s26 = new Services(timeSlots, sp9, jan26);
			srepo.save(sp9s26);
			Services sp9s27 = new Services(timeSlots, sp9, jan27);
			srepo.save(sp9s27);
			Services sp9s28 = new Services(timeSlots, sp9, jan28);
			srepo.save(sp9s28);
			Services sp9s29 = new Services(timeSlots, sp9, jan29);
			srepo.save(sp9s29);
			Services sp9s30 = new Services(timeSlots, sp9, jan30);
			srepo.save(sp9s30);
			Services sp9s31 = new Services(timeSlots, sp9, jan31);
			srepo.save(sp9s31);
			
			
//			----- Reservation - sp9, Basic dog training------
			

			Reservation ressp9s1_e = new Reservation(sp9s1, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp9s1_e);
			

			Reservation ressp9s2_a = new Reservation(sp9s2, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp9s2_a);
			Reservation ressp9s2_b = new Reservation(sp9s2, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp9s2_b);
			Reservation ressp9s2_c = new Reservation(sp9s2, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp9s2_c);
			Reservation ressp9s2_d = new Reservation(sp9s2, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp9s2_d);
			Reservation ressp9s2_e = new Reservation(sp9s2, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp9s2_e);
			
			
			Reservation ressp9s3_a = new Reservation(sp9s3, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp9s3_a);
			Reservation ressp9s3_b = new Reservation(sp9s3, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp9s3_b);
			Reservation ressp9s3_c = new Reservation(sp9s3, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp9s3_c);
			Reservation ressp9s3_d = new Reservation(sp9s3, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp9s3_d);
			Reservation ressp9s3_e = new Reservation(sp9s3, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp9s3_e);	
			

			Reservation ressp9s4_b = new Reservation(sp9s4, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp9s4_b);


			Reservation ressp9s8_e = new Reservation(sp9s8, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp9s8_e);

			Reservation ressp9s9_a = new Reservation(sp9s9, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp9s9_a);
			Reservation ressp9s9_b = new Reservation(sp9s9, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp9s9_b);
			Reservation ressp9s9_c = new Reservation(sp9s9, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp9s9_c);
			Reservation ressp9s9_d = new Reservation(sp9s9, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp9s9_d);
			Reservation ressp9s9_e = new Reservation(sp9s9, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp9s9_e);

			Reservation ressp9s10_a = new Reservation(sp9s10, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp9s10_a);
			Reservation ressp9s10_b = new Reservation(sp9s10, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp9s10_b);
			Reservation ressp9s10_c = new Reservation(sp9s10, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp9s10_c);
			Reservation ressp9s10_d = new Reservation(sp9s10, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp9s10_d);
			Reservation ressp9s10_e = new Reservation(sp9s10, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp9s10_e);


			Reservation ressp9s15_c = new Reservation(sp9s15, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp9s15_c);

			Reservation ressp9s16_a = new Reservation(sp9s16, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp9s16_a);
			Reservation ressp9s16_b = new Reservation(sp9s16, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp9s16_b);
			Reservation ressp9s16_c = new Reservation(sp9s16, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp9s16_c);
			Reservation ressp9s16_d = new Reservation(sp9s16, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp9s16_d);
			Reservation ressp9s16_e = new Reservation(sp9s16, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp9s16_e);

			Reservation ressp9s17_a = new Reservation(sp9s17, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp9s17_a);
			Reservation ressp9s17_b = new Reservation(sp9s17, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp9s17_b);
			Reservation ressp9s17_c = new Reservation(sp9s17, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp9s17_c);
			Reservation ressp9s17_d = new Reservation(sp9s17, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp9s17_d);

			Reservation ressp9s18_a = new Reservation(sp9s18, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp9s18_a);


			Reservation ressp9s22_b = new Reservation(sp9s23, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp9s22_b);
			

			Reservation ressp9s23_a = new Reservation(sp9s24, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp9s23_a);
			Reservation ressp9s23_b = new Reservation(sp9s24, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp9s23_b);
			Reservation ressp9s23_c = new Reservation(sp9s24, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp9s23_c);
			Reservation ressp9s23_d = new Reservation(sp9s24, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp9s23_d);
			Reservation ressp9s23_e = new Reservation(sp9s24, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp9s23_e);
			
			Reservation ressp9s24_b = new Reservation(sp9s24, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp9s24_b);
			Reservation ressp9s24_c = new Reservation(sp9s24, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp9s24_c);
			Reservation ressp9s24_d = new Reservation(sp9s24, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp9s24_d);
			Reservation ressp9s24_e = new Reservation(sp9s24, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp9s24_e);


			Reservation ressp9s29_e = new Reservation(sp9s29, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp9s29_e);

			Reservation ressp9s30_a = new Reservation(sp9s30, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp9s30_a);
			Reservation ressp9s30_b = new Reservation(sp9s30, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp9s30_b);
			Reservation ressp9s30_c = new Reservation(sp9s30, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp9s30_c);
			Reservation ressp9s30_d = new Reservation(sp9s30, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp9s30_d);
			Reservation ressp9s30_e = new Reservation(sp9s30, "5pm", u7, Status.ACTIVE);
			resrepo.save(ressp9s30_e);

			Reservation ressp9s31_a = new Reservation(sp9s31, "8am", u3, Status.ACTIVE);
			resrepo.save(ressp9s31_a);
			Reservation ressp9s31_b = new Reservation(sp9s31, "10am", u4, Status.ACTIVE);
			resrepo.save(ressp9s31_b);
			Reservation ressp9s31_c = new Reservation(sp9s31, "1pm", u5, Status.ACTIVE);
			resrepo.save(ressp9s31_c);
			Reservation ressp9s31_d = new Reservation(sp9s31, "3pm", u6, Status.ACTIVE);
			resrepo.save(ressp9s31_d);

			
			
			
//			----- Lost&Found Post ------
			
			  BufferedImage bImage99 = ImageIO.read(new File("src/main/resources/static/images/1.jpg"));
		      ByteArrayOutputStream bos99 = new ByteArrayOutputStream();
		      ImageIO.write(bImage, "jpg", bos99);
		      byte [] data1 = bos99.toByteArray();
		      
		      BufferedImage bImage100 = ImageIO.read(new File("src/main/resources/static/images/2.jpg"));
		      ByteArrayOutputStream bos100 = new ByteArrayOutputStream();
		      ImageIO.write(bImage2, "jpg", bos100);
		      byte [] data2 = bos100.toByteArray();

		      
		      Post pt4 = new Post(PostType.LOST, "Have you seen my dog?", "Dog lost around Serangoon Gardens. Its name is Max. It was lost in the afternoon. Did anyone see it?", data1, u1);
		
		      Post pt5 = new Post(PostType.FOUND, "Dog roaming around NUS", "Saw a black shih tzu roaming around school today, HMU if you are its owner. The dog tag says that its name is Jackie. I fed it some food already. Hope to return it to the owner soon.", data2, u2);
		      
		      ptrepo.save(pt4);
		      
		      ptrepo.save(pt5);
		      
		      PostComment pc1 = new PostComment("I think I have seen it too", LocalDateTime.now(), u1, pt4); 
		      PostComment pc2 = new PostComment("Looks cute!", LocalDateTime.now().minusMinutes(1), u1, pt5); 
		      PostComment pc3 = new PostComment("Have you seen it too?", LocalDateTime.now().minusMinutes(2), u1, pt4); 
		      PostComment pc4 = new PostComment("May have spotted it around the bus stop.", LocalDateTime.now().minusMinutes(3), u2, pt5); 
		      PostComment pc5 = new PostComment("Is it found yet?", LocalDateTime.now().minusMinutes(4), u1, pt4); 
		      PostComment pc6 = new PostComment("I hope it is returned to its owner soon", LocalDateTime.now().minusMinutes(5), u2, pt5); 
		      PostComment pc7 = new PostComment("Yes. It has been returned.", LocalDateTime.now().minusMinutes(6), u1, pt4); 
		      PostComment pc8 = new PostComment("Are you sure?", LocalDateTime.now().minusMinutes(7), u1, pt5); 
		      PostComment pc9 = new PostComment("Yes, really sure it has been returned. Thanks everyone for the help.", LocalDateTime.now().minusMinutes(8), u2, pt4); 
		      PostComment pc10 = new PostComment("That's great!", LocalDateTime.now().minusMinutes(9), u1, pt5); 
		      PostComment pc11 = new PostComment("Saw it at the bus stop", LocalDateTime.now().minusMinutes(10), u1, pt4); 
		      PostComment pc12 = new PostComment("Looks nice!", LocalDateTime.now().minusMinutes(11), u1, pt5); 
		      PostComment pc13 = new PostComment("Maybe my friend saw it", LocalDateTime.now().minusMinutes(12), u1, pt4); 
		      PostComment pc14 = new PostComment("May have spotted it around the canteen.", LocalDateTime.now().minusMinutes(13), u2, pt5); 
		      PostComment pc15 = new PostComment("Found??", LocalDateTime.now().minusMinutes(14), u1, pt4); 
		      PostComment pc16 = new PostComment("Go home soon", LocalDateTime.now().minusMinutes(15), u2, pt5); 
		      PostComment pc17 = new PostComment("It came home yesterday", LocalDateTime.now().minusMinutes(16), u1, pt4); 
		      PostComment pc18 = new PostComment("Yes!", LocalDateTime.now().minusMinutes(17), u1, pt5); 
		      PostComment pc19 = new PostComment("Thanks everyone for the help.", LocalDateTime.now().minusMinutes(18), u2, pt4); 
		      PostComment pc20 = new PostComment("That's good!", LocalDateTime.now().minusMinutes(19), u1, pt5); 
		      PostComment pc21 = new PostComment("Looks like my dog", LocalDateTime.now().minusMinutes(20), u1, pt4); 
		      PostComment pc22 = new PostComment("Looks nice", LocalDateTime.now().minusMinutes(21), u1, pt5); 
		      PostComment pc23 = new PostComment("Stolen?", LocalDateTime.now().minusMinutes(22), u1, pt4); 
		      PostComment pc24 = new PostComment("What is its name?", LocalDateTime.now().minusMinutes(23), u2, pt5); 
		      PostComment pc25 = new PostComment("Is it found yet?", LocalDateTime.now().minusMinutes(24), u1, pt4); 
		      PostComment pc26 = new PostComment("I hope it is returned to its owner soon", LocalDateTime.now().minusMinutes(25), u2, pt5); 
		      PostComment pc27 = new PostComment("Yes. It has been returned.", LocalDateTime.now().minusMinutes(26), u1, pt4); 
		      PostComment pc28 = new PostComment("Are you sure?", LocalDateTime.now().minusMinutes(27), u1, pt5); 
		      PostComment pc29 = new PostComment("Yes, really sure it has been returned. Thanks everyone for the help.", LocalDateTime.now().minusMinutes(28), u2, pt4); 
		      PostComment pc30 = new PostComment("That's great!", LocalDateTime.now().minusMinutes(29), u1, pt5); 
		      
		      pcrepo.save(pc1); pcrepo.save(pc2); pcrepo.save(pc3); pcrepo.save(pc4); pcrepo.save(pc5);pcrepo.save(pc6);pcrepo.save(pc7);pcrepo.save(pc8);pcrepo.save(pc9);pcrepo.save(pc10);
		      pcrepo.save(pc11); pcrepo.save(pc12); pcrepo.save(pc13); pcrepo.save(pc14); pcrepo.save(pc15);pcrepo.save(pc16);pcrepo.save(pc17);pcrepo.save(pc18);pcrepo.save(pc19);pcrepo.save(pc20);
		      pcrepo.save(pc21); pcrepo.save(pc22); pcrepo.save(pc23); pcrepo.save(pc24); pcrepo.save(pc25);pcrepo.save(pc26);pcrepo.save(pc27);pcrepo.save(pc28);pcrepo.save(pc29);pcrepo.save(pc30);

		};
	}
}
