package senarious;
import java.util.*;
import java.io.*;
public class IPLMain {

    public static void main(String args[]) {
        String matches = "/home/rohit/Downloads/matches.csv";
        String deliveries = "/home/rohit/Downloads/deliveries.csv";
           Scanner scan=new Scanner(System.in);

          while(true){
              System.out.println("1> Number of matches played per year of all the years in IPL.");
              System.out.println("2> Number of matches won of all teams over all the years of IPL.");
              System.out.println("3> Number of matches won of all teams over all the years of IPL.");
              System.out.println("4> For the year 2015 get the top economical bowlers.");
              System.out.println("5> Exit from program");

                int choice=scan.nextInt();

              switch (choice){
                  case 1:
                      IPLMain.numberOfMatchPerYear(matches);
                        break;
                  case 2:
                      IPLMain.numberOfMatchesWonAllTeamInIpl(matches);
                        break;
                  case 3:
                      IPLMain.getExtraRunPerTeamIn2016(matches,deliveries);
                       break;
                  case 4:
                      IPLMain.findEconomicalBowler(matches,deliveries);
                      break;
                  case 5:
                           System.exit(0);
                  default:
                        System.out.println("Invalid option");

              }

          }

    }


    public static void numberOfMatchPerYear(String file) {

        try {

            String line = "";

            BufferedReader reader = new BufferedReader(new FileReader(file));
            TreeMap<String, Integer> numberOfMatchPerYear = new TreeMap<String, Integer>();
            reader.readLine();
            while ((line = reader.readLine()) != null) {


                String[] matchDetail = line.split(",");

                if (!numberOfMatchPerYear.containsKey(matchDetail[1])) {
                    numberOfMatchPerYear.put(matchDetail[1], 1);
                } else {
                    numberOfMatchPerYear.put(matchDetail[1], numberOfMatchPerYear.getOrDefault(matchDetail[1], 0) + 1);
                }
            }


            for (Map.Entry<String, Integer> entry : numberOfMatchPerYear.entrySet()) {
                System.out.println("Number of matches in " + entry.getKey() + " is : " + entry.getValue());
            }


        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void numberOfMatchesWonAllTeamInIpl(String file) {


        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            Map<String, Integer> wonMatchesInIpl = new HashMap<>();

            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] matchDetail = line.split(",");

                if (!wonMatchesInIpl.containsKey(matchDetail[10]))
                    wonMatchesInIpl.put(matchDetail[10], 1);
                else
                    wonMatchesInIpl.put(matchDetail[10], wonMatchesInIpl.getOrDefault(matchDetail[10], 0) + 1);

            }

            for (Map.Entry<String, Integer> entry : wonMatchesInIpl.entrySet()) {
                System.out.println(entry.getKey() + " won :" + entry.getValue() + " in Ipl.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void getExtraRunPerTeamIn2016(String matches, String deliveries) {

        try {
            BufferedReader readerMatches = new BufferedReader(new FileReader(matches));

            Map<String, Integer> getExtraRun = new HashMap<>();
            String lineM = "";
            String lineD = "";

            readerMatches.readLine();

            while ((lineM = readerMatches.readLine()) != null) {

                String[] matchDetail = lineM.split(",");

                if (matchDetail[1].equals("2016")) {
                    BufferedReader readerDeliveries = new BufferedReader(new FileReader(deliveries));
                    readerDeliveries.readLine();
                    while ((lineD = readerDeliveries.readLine()) != null) {

                        String[] deliveryDetail = lineD.split(",");
                        if (matchDetail[0].equals(deliveryDetail[0])) {
                            getExtraRun.put(deliveryDetail[2], getExtraRun.getOrDefault(deliveryDetail[2], 0) + Integer.parseInt(deliveryDetail[16]));
                        }
                    }
                }

            }

            for (Map.Entry<String, Integer> entry : getExtraRun.entrySet()) {
                System.out.println(entry.getKey() + " get extra run in 2016 is : " + entry.getValue());
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static class EconomicalBowler{
        long run;
        long bowl;

        EconomicalBowler(long run,long bowl){
            this.run=run;
            this.bowl=bowl;
        }
    }



    public static void findEconomicalBowler(String matches,String deliveries){

        try{
            BufferedReader readerMatches = new BufferedReader(new FileReader(matches));
            Map<String,EconomicalBowler>  bowler=new HashMap<>();
            String lineMatches="";
            readerMatches.readLine();

            while((lineMatches= readerMatches.readLine())!=null) {

                String matchDetails[]=lineMatches.split(",");

                if(matchDetails[1].equals("2015")) {

                    BufferedReader readerDeliveries = new BufferedReader(new FileReader(deliveries));
                    String lineDeliveries = "";

                    while ((lineDeliveries = readerDeliveries.readLine()) != null) {

                        String[] deliveriesDetails = lineDeliveries.split(",");

                        if(matchDetails[0].equals(deliveriesDetails[0])) {

                            if (!bowler.containsKey(deliveriesDetails[8])) {
                                bowler.put(deliveriesDetails[8], new EconomicalBowler(Integer.parseInt(deliveriesDetails[17]), 1));
                            } else {
                                EconomicalBowler eb = bowler.get(deliveriesDetails[8]);
                                eb.bowl += 1;
                                eb.run += Integer.parseInt(deliveriesDetails[17]);
                                bowler.put(deliveriesDetails[8], eb);
                            }
                        }
                    }

                }
            }

            String bowlerName="";
            double rate=Double.MAX_VALUE;

            for(Map.Entry<String, EconomicalBowler> entry: bowler.entrySet()){
                EconomicalBowler eb= entry.getValue();
                int runs=(int)eb.run;
                double over=(double)eb.bowl/6;
                if((double)runs/over<rate){
                    bowlerName= entry.getKey();
                }

            }

            System.out.println("Economical Bowler is :"+bowlerName+" in 2015.");

        }catch(Exception e){
                System.out.println(e);
        }

    }

}