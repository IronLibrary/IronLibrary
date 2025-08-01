package org.example.util;

import java.io.IOException;

public class PauseUtil {
        public static void pause(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException ignored) {}
        }
        // pausar hasta que se presione una tecla
        public static void pauseUntilKeyPress() {
            System.out.println("Press Enter to continue...");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

