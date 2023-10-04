package in.mindcraft.CRUD;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Tester {
	
	public static void main(String[] args) {
	
		Configuration con = new Configuration().configure();
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session =sf.openSession();
			
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("\n =================Welcome============================================================================");
			System.out.println(" 1.Create Record");
			System.out.println(" 2.Read Record");
			System.out.println(" 3.Update Record");
			System.out.println(" 4.Delete Record");
			System.out.println(" 5.Exit");
			System.out.println("\n ====================================================================================================");
			System.out.println("\n Enter Choice:");
			int choice = sc.nextInt();
			
			switch (choice) {
				case 1:
					Transaction t1 = session.beginTransaction();
					System.out.println("\n ====================================================================================================");
					System.out.println(" Enter Mobile Id");
					int id = sc.nextInt();
					System.out.println(" Enter Mobile Make");
					String make = sc.next();
					System.out.println(" Enter Mobile Cost");
					double cost = sc.nextDouble();
					
					MobilePhone m = new MobilePhone();
					
					m.setMid(id);
					m.setMake(make);
					m.setCost(cost);
					try {
						session.save(m);
						t1.commit();
						System.out.println(" Inserted Data Successfully");
						System.out.println("\n ====================================================================================================");				
						MobilePhone mi  = (MobilePhone)session.get(MobilePhone.class, id);
						System.out.println(" "+mi);
						System.out.println("\n ====================================================================================================");
						
					} catch (Exception e){
						
						System.out.println("\n ====================================================================================================");
						t1.rollback();
						System.out.println(e+"\n \n Data already Exist on"+id+" try again");
						System.out.println("\n ====================================================================================================");					
												
					}
					
					
					System.out.println("\n ====================================================================================================");
					break;
				case 2:
					System.out.println("\n ====================================================================================================");
					
					Query dquery = session.createQuery("from MobilePhone ");
					@SuppressWarnings("unchecked") 
					List<MobilePhone> dlist = dquery.list();
					for(MobilePhone md:dlist)
						System.out.println(" "+ md.getMid()+"\t"+md.getMake()+"\t"+md.getCost());
					
					System.out.println("\n ====================================================================================================");				
					break;
				case 3:
					Transaction t3 = session.beginTransaction();
					System.out.println("\n ====================================================================================================");				
					
					System.out.println(" Enter Mobile Id for Updation: \n");
					int sid = sc.nextInt();
					MobilePhone mu = (MobilePhone)session.get(MobilePhone.class, sid);
					
					System.out.println(" Enter New Make Name:\n");
					String umake = sc.next();
					mu.setMake(umake);
					System.out.println(" Enter New Cost:\n");
					double ucost = sc.nextDouble();
					mu.setCost(ucost);
					t3.commit();
					System.out.println(" Records Updated for Mobile Id"+sid);
					
					System.out.println("\n ====================================================================================================");				
					MobilePhone mu2  = (MobilePhone)session.get(MobilePhone.class, sid);
					System.out.println(" "+mu2);
					System.out.println("\n ====================================================================================================");				
					break;
					
				case 4:
					Transaction t4 = session.beginTransaction();
					System.out.println("\n ====================================================================================================");				
					
					System.out.println(" Enter Mobile Id for Deletion: \n");
					int did = sc.nextInt();
					MobilePhone mdel = (MobilePhone)session.get(MobilePhone.class, did);
					session.delete(mdel);
					t4.commit();
					
					System.out.println("\n ====================================================================================================");				
					MobilePhone mdel2  = (MobilePhone)session.get(MobilePhone.class, did);
					System.out.println(" "+mdel2);
					System.out.println("\n ====================================================================================================");				
					break;
				
				case 5:
					System.out.println("\n ====================================================================================================");				
					System.out.println(" Thankyou");
					System.out.println("\n ====================================================================================================");				
					System.exit(0);
					break;
					
				default:
					System.out.println(" Try Again");
			}
			sc.close();
		}	
		
	}

}
