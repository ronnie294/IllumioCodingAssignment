import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;


/**
 *Firewall class which first loads up a set of rules, then validates a packet against those rules.
 *
 */
public class Firewall {
  private static Set<Rule> rules = new HashSet<Rule>();
  protected Firewall(String filename){
    try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while((line = br.readLine()) != null) {
        String [] rule = line.split(",");
        if (rule[2].contains("-")) {
          multiplePorts(rule);
        }
        else{
          if (rule[3].contains("-")) {
            multipleAddress(rule);
          }
          else
          {
            Rule currRule = new Rule(rule[0],rule[1],rule[2],rule[3]);
            rules.add(currRule);
          }
        }
      }
    }
    catch (FileNotFoundException e) {
      System.out.println("Cannot find file "+ filename );
    }
    catch(Exception e) {
      System.out.println("Exeption occured " + e.getMessage());
    }
  }

  /**
   * Represents the case when there is a range of ports.
   * @param rule
   */
  private void multiplePorts(String[] rule) {
    String [] portRanges = rule[2].split("-");
    int portMin = Integer.parseInt(portRanges[0]);
    int portMax = Integer.parseInt(portRanges[1]);
    int portRange = portMax - portMin;

    if (rule[3].contains("-")) {
      String [] ipAddressRanges = rule[3].split("-");
      long ipAddressMin = Long.parseLong(ipAddressRanges[0].replaceAll("\\.", ""));
      long ipAddressMax = Long.parseLong(ipAddressRanges[1].replaceAll("\\.", ""));
      long ipRange = ipAddressMax - ipAddressMin;
      for (int i = 0; i <= portRange; i++) {
        for (int j = 0; j <= ipRange; j++) {
          Rule currRule = new Rule(rule[0], rule[1], portMin + i, ipAddressMin + j);
          rules.add(currRule);
        }
      }
    }else{
      for (int i = 0; i <= portRange; i++) {
        Rule currRule = new Rule(rule[0],rule[1], portMin + i, rule[3]);
        rules.add(currRule);
      }
    }
  }

  /**
   * Represents the condition when there is a range of addresses.
   * @param rule
   */
  private void multipleAddress(String[] rule) {
    String [] ipAddressRanges = rule[3].split("-");
    long ipAddressMin = Long.parseLong(ipAddressRanges[0].replaceAll("\\.", ""));
    long ipAddressMax = Long.parseLong(ipAddressRanges[1].replaceAll("\\.", ""));
    long ipRange = ipAddressMax - ipAddressMin;
    for (int i = 0; i <= ipRange; i++) {
      Rule currRule = new Rule(rule[0],rule[1],rule[2], ipAddressMin + i);
      rules.add(currRule);
    }
  }

  /**
   * This function decides whether to accept the packet or not by looking up to the rule Set.
   * @param direction
   * @param protocol
   * @param port
   * @param ipAddress
   * @return
   */
  public boolean acceptPacket(String direction, String protocol, int port, String ipAddress) {
    if ((!direction.equals("inbound")) && !direction.equals("outbound")) {
      throw new IllegalArgumentException("Enter Correct value for direction");
    }
    if ((!protocol.equals("tcp")) && !protocol.equals("udp")) {
      throw new IllegalArgumentException("Enter Correct value for protocol");
    }
    Rule rule = new Rule(direction, protocol, port, ipAddress);
    return rules.contains(rule);
  }




}