package gx.josql;

import org.josql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 2017/5/9.
 */
public class JosqlTest {

    public static void main(String args[]) {
        List<MyObject> obs = getObs();
        Query q = new Query();
        String sql = "select name, age from gx.josql.MyObject order by age";
        try {
            q.parse(sql);
            QueryResults qr = q.execute (obs);
            List res = qr.getResults ();
            for (int i = 0; i< res.size(); i++) {
                System.out.println(res.get(i));
            }
        }catch (Exception e) {
            System.out.print(e);
        }

    }

    public static List<MyObject> getObs() {
        List<MyObject> obs = new ArrayList<MyObject>();
        obs.add(new MyObject("joe", 12));
        obs.add(new MyObject("cat", 22));
        obs.add(new MyObject("bas", 42));
        obs.add(new MyObject("foo", 32));
        return obs;

    }



}
