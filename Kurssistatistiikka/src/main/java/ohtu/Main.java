package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";
        String url2 = "https://studies.cs.helsinki.fi/courses/courseinfo";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        String bodyText2 = Request.Get(url2).execute().returnContent().asString();
        
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course[] courses = mapper.fromJson(bodyText2, Course[].class);
        
        JsonParser parser = new JsonParser();
        
        int exerciseTotal = 0;
        int courseExerciseTotal = 0;
        int hoursTotal = 0;       
        String courseName = "";
        System.out.println("Opiskelijanumero "+ studentNr);
        
        for (Course course : courses) {
            for (Submission submission : subs) {
                if(submission.getCourse().equals(course.getName())) {
                    courseName = course.getName();
                    if(courseExerciseTotal == 0) {
                        System.out.println(); 
                        System.out.println(course.getFullName());
                        System.out.println();       
                    }
                    submission.setCourseInfo(course);
                    exerciseTotal += submission.getExercises().length;
                    courseExerciseTotal += course.getExercises()[submission.getWeek()];
                    hoursTotal += submission.getHours();
                    System.out.println(submission.toString());
                }   
            }
            if(courseExerciseTotal > 0) {
            System.out.println();  
            System.out.println("yhteensä: " + exerciseTotal + "/" + courseExerciseTotal + " tehtävää " + hoursTotal + " tuntia");
            System.out.println();
            String osoite = "https://studies.cs.helsinki.fi/courses/" + courseName + "/stats";
            String json = Request.Get(osoite).execute().returnContent().asString();
            JsonObject jsonData = parser.parse(json).getAsJsonObject();
            int returnees = 0;
            int returns = 0;
            int timeSpent = 0;
            for(int i = 1; i < 5; i++) {
                JsonObject week = jsonData.get(""+i+"").getAsJsonObject();
                returnees += week.get("students").getAsInt();
                returns += week.get("exercise_total").getAsInt();
                timeSpent += week.get("hour_total").getAsInt();
            }
            System.out.println("kurssilla yhteensä " + returnees + " palautusta, palautettuja tehtäviä " + returns + " kpl, aikaa käytetty yhteensä " + timeSpent + " tuntia");
            System.out.println();  
            }

            exerciseTotal = 0;
            courseExerciseTotal = 0;
            hoursTotal = 0; 
        }
    }
}