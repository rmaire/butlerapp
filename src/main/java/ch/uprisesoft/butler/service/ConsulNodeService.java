/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.service;

import ch.uprisesoft.butler.model.Host;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.catalog.model.Node;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rmaire
 */
public class ConsulNodeService implements NodeService {

    private final String consulAddress;

    public ConsulNodeService(String consulAddress) {
        this.consulAddress = consulAddress;
    }

    @Override
    public List<Host> getNodes() {
        List<Host> nodes = new ArrayList<>();
        ConsulClient client = new ConsulClient(consulAddress);
        Response<List<Node>> consulNodes = client.getCatalogNodes(QueryParams.DEFAULT);
        String[] leaderAddressParts = client.getStatusLeader().getValue().split(":");
        String leader = leaderAddressParts[0];

        for (Node n : consulNodes.getValue()) {
            Boolean isLeader = n.getAddress().equals(leader);
            Host node = new Host(n.getAddress(), n.getNode(), isLeader, "", "");
            nodes.add(node);
        }
        return nodes;
    }

}
