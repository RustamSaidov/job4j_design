package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        boolean serverOffFlag = false;
        String intervalBeginning = "";
        String intervalEnd;
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
                for (String line = in.readLine(); line != null; line = in.readLine()) {
                    if ((line.contains("400") || line.contains("500")) && !serverOffFlag) {
                        intervalBeginning = line.substring(4);
                        serverOffFlag = true;
                    }
                    if (!(line.contains("400") || line.contains("500")) && serverOffFlag) {
                        intervalEnd = line.substring(4);
                        serverOffFlag = false;
                        out.println(intervalBeginning + ";" + intervalEnd + ";");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server_log.txt", "./data/log_analysis_result_file.txt");
    }
}