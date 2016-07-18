package com.app.constants;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

public final class UuidGenerator {

  private static String HOST_IP;

  static {
    try {
      HOST_IP = InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      HOST_IP = "127.0.0.1"; // localhost
    }
  }

  // can't instantiate from outside
  private UuidGenerator() {
  }

  /**
   * Generates a {@link UUID} from the current Host IP Address and Epoch Time.
   * 
   * @return the generated {@link UUID}.
   */
  public static UUID fromHostAndCurrentTime() {
    String currentTime = Long.toString(System.currentTimeMillis());
    byte[] genBytes = (HOST_IP + currentTime).getBytes();
    return UUID.nameUUIDFromBytes(genBytes);
  }
  
}