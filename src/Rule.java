public class Rule {

  private String direction;
  private String protocol;
  private int port;
  private long ipAddress;

  /**
   Constructor Overloading so that we can easily create rules from the firewall class.
   */
  public Rule(String direction, String protocol, String port, String ipAddress) {
    this.direction = direction;
    this.protocol = protocol;
    this.port = Integer.parseInt(port);
    this.ipAddress = Long.parseLong(ipAddress.replaceAll("\\.", ""));
  }

  public Rule(String direction, String protocol, String port, long ipAddress) {
    this.direction = direction;
    this.protocol = protocol;
    this.port = Integer.parseInt(port);
    this.ipAddress = ipAddress;
  }

  public Rule(String direction, String protocol, int port, long ipAddress) {
    this.direction = direction;
    this.protocol = protocol;
    this.port = port;
    this.ipAddress = ipAddress;
  }

  public Rule(String direction, String protocol, int port, String ipAddress) {
    this.direction = direction;
    this.protocol = protocol;
    this.port = port;
    this.ipAddress = Long.parseLong(ipAddress.replaceAll("\\.", "")); //convert string ipAddress with periods to just a number
  }

  /**
   * This is overwritten in order to state that 2 network rule are similar when direction,
   * protocol, port and IP address are same.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Rule)) return false;
    Rule networkRule = (Rule) o;
    return  direction.equalsIgnoreCase(networkRule.direction) && protocol.equalsIgnoreCase(networkRule.protocol)
            && port == networkRule.port && ipAddress == networkRule.ipAddress;
  }

  @Override
  public String toString() {
    return this.direction +  ", " + this.protocol + ", " + Integer.toString(this.port) + ", " + Long.toString(this.ipAddress);
  }


  /**
   * Overridinh Hashcode with equals is considered a good practice.
   * @return
   */
  public int hashCode() {
    long hash =  31 * (this.ipAddress + this.port + this.direction.hashCode() + this.protocol.hashCode());
    return Long.valueOf(hash).hashCode();
  }

}