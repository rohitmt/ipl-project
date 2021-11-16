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

                int choice=scan.nextInt();

              switch (choice){
                  case 1:
                      IPLMain.numberOfMatchPerYear(matches);
                        break;
                  case 2:
                    //  IPLMain.numberOfMatchesWonAllTeamInIpl(matches);
                        break;
                  case 3:
                    //  IPLMain.getExtraRunPerTeamIn2016(matches,deliveries);
                       break;
                  case 4:
                     // IPLMain.findEconomicalBowler(matches,deliveries);
                      break;
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


}