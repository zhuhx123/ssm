package cn.com.ssm.icetest.common.ice.server;


import Ice.Application;
import Ice.ObjectAdapter;

/**
 * Created by Nathy on 2018/1/29.
 */
public class IceServer extends Application implements Runnable {
    private static String serverName;

    public void init(String[] args) {
        ObjectAdapter adapter = communicator().createObjectAdapter(serverName);
//        adapter.add(impl[0], Ice.Util.stringToIdentity(""));
    }


    public void run() {

    }

    @Override
    public int run(String[] strings) {
        return 0;
    }
}
