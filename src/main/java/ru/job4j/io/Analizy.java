package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        boolean serverOffFlag = false;
        String intervalBeginning = "";
        String intervalEnd;
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if ((line.contains("400") || line.contains("500")) && !serverOffFlag) {
                    //filterList.add(line);
                    intervalBeginning = line.substring(4);
                    serverOffFlag = true;
                }
                if (!(line.contains("400") || line.contains("500")) && serverOffFlag) {
                    intervalEnd = line.substring(4);
                    serverOffFlag = false;
                    list.add(intervalBeginning + ";" + intervalEnd + ";");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (int i = 0; i < list.size(); i++) {
                out.println(list.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server_log.txt", "./data/log_analysis_result_file.txt");
        /*try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}