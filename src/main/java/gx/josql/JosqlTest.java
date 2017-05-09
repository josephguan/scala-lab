package gx.josql;

import org.josql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Joe on 2017/5/9.
 */
public class JosqlTest {

    public static void main(String args[]) {
        List<MyObject> obs = getObs();
        Query q = new Query();
        String sql = "select name, @avg_length from gx.josql.MyObject group by name order by name execute on GROUP_BY_RESULTS avg (:_allobjs, age) AS avg_length";
        try {
            q.parse(sql);
            QueryResults qr = q.execute (obs);
            List res = qr.getResults ();
            Map gres = qr.getGroupByResults();

            for (Object key : gres.keySet()) {

                System.out.println("Key = " + key);

            }

            for (int i = 0; i< res.size(); i++) {
                System.out.println(gres.get(res.get(i)));
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
        obs.add(new MyObject("foo", 30));
        return obs;

    }



}
