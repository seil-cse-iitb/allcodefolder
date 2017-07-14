package com.example.sid.aravali_v_21;

/**
 * Created by sid on 29/12/16.
 */

        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.HashMap;
        import java.util.LinkedHashMap;
        import java.util.List;

public class ExpandableListDataPump {
    public static LinkedHashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();


        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH)+1;
        String m1,m2,m3,m4,m5;
        m1=m2=m3=m4=m5="";
        switch (month){

            case 1:{

                m1 = "December";
                m2= "November";
                m3 = "October";
                m4 = "September";
                m5 = "August";


            }
            break;
            case 2:{


                m2 = "December";
                m3= "November";
                m4 = "October";
                m5 = "September";
                m1 = "January";


            }
            break;
            case 3:{


                m3 = "December";
                m4= "November";
                m5 = "October";
                m1 = "February";
                m2 = "January";


            }
            break;
            case 4:{


                m4 = "December";
                m5= "November";
                m1 = "March";
                m2 = "February";
                m3 = "January";
            }
            break;
            case 5:{



            }
            break;
            case 6:{


            }
            break;
            case 7:{


            }
            break;
            case 8:{


            }
            break;
            case 9:{


            }
            break;
            case 10:{


            }
            break;
            case 11:{


            }
            break;
            case 12:{

                m4 = "August";
                m5= "July";
                m1 = "November";
                m2 = "October";
                m3 = "September";
            }
            break;



        }


        List<String> cricket = new ArrayList<String>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");
        cricket.add("England");
        cricket.add("South Africa");

        List<String> football = new ArrayList<String>();
        football.add("Brazil");
        football.add("Spain");
        football.add("Germany");
        football.add("Netherlands");
        football.add("Italy");

        List<String> basketball = new ArrayList<String>();
        basketball.add("United States");
        basketball.add("Spain");
        basketball.add("Argentina");
        basketball.add("France");
        basketball.add("Russia");

        expandableListDetail.put(m1, cricket);
        expandableListDetail.put(m2, football);
        expandableListDetail.put(m3, basketball);
        expandableListDetail.put(m4, football);
        expandableListDetail.put(m5, basketball);

        return expandableListDetail;
    }
    String[] months = {"January","February","March","April","May","June","July","August","September","Octuber","November","December"};
}