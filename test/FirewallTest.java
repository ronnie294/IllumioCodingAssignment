import org.junit.Test;
import static org.junit.Assert.*;

public class FirewallTest {

  @Test
  public void fireTest(){
    Firewall f = new Firewall("rules.csv");
    boolean test1 = f.acceptPacket("outbound", "tcp", 20000, "192.168.10.11");
    boolean test2 = f.acceptPacket("inbound", "tcp",80,"192.168.1.2");
    boolean test3 = f.acceptPacket("inbound", "tcp",80,"192.168.1.322");
    boolean test4 = f.acceptPacket("inbound", "udp",43,"12.53.6.25");
    boolean test5 = f.acceptPacket("inbound", "tcp",673,"123.45.56.83");
    assertTrue(test1);
    assertTrue(test2);
    assertFalse(test3);
    assertFalse(test4);
    assertTrue(test5);
  }

}
