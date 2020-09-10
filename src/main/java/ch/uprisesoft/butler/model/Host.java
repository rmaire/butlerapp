package ch.uprisesoft.butler.model;


public class Host {

    private final String ip;
    private final String name;
    private final boolean leader;
    private final String user;
    private final String key;

    public Host(String ip, String name, boolean leader, String user, String key) {
        this.ip = ip;
        this.name = name;
        this.leader = leader;
        this.user = user;
        this.key = key;
    }

    public String getIp() {
        return ip;
    }

    public String getName() {
        return name;
    }

    public boolean isLeader() {
        return leader;
    }

 /*   public Target getTarget() {
        return new Target.Builder()
                .withHost(ip)
                .withUsername(user)
                .withKey(key)
                .build();
    }*/

    @Override
    public String toString() {
        return "Host{" + "ip=" + ip + ", name=" + name + ", leader=" + leader + ", user=" + user + ", key=" + key + '}';
    }

}
