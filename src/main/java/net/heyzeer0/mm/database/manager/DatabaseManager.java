package net.heyzeer0.mm.database.manager;

import com.rethinkdb.net.Connection;
import net.heyzeer0.mm.database.entities.AnnounceProfile;
import net.heyzeer0.mm.database.entities.MarketProfile;
import net.heyzeer0.mm.database.entities.UserProfile;
import org.bukkit.entity.Player;

import static com.rethinkdb.RethinkDB.r;

/**
 * Created by HeyZeer0 on 15/08/2017.
 * Copyright © HeyZeer0 - 2016
 */
public class DatabaseManager {

    private Connection conn;

    public DatabaseManager(Connection conn) {
        this.conn = conn;

        try{
            r.tableCreate("users").run(conn);
            r.tableCreate("listings").run(conn);
            r.tableCreate("announces").run(conn);
        }catch (Exception ignored) {}
    }

    public UserProfile getUserProfile(Player p) {
        UserProfile data = r.table(UserProfile.DB_TABLE).get(p.getUniqueId().toString()).run(conn, UserProfile.class);
        return data != null ? data : new UserProfile(p);
    }

    public MarketProfile getServerMarket(String name) {
        MarketProfile data = r.table(MarketProfile.DB_TABLE).get(name).run(conn, MarketProfile.class);
        return data != null ? data : new MarketProfile(name);
    }

    public AnnounceProfile getAnnounce(String uuid) {
        AnnounceProfile data = r.table(AnnounceProfile.DB_TABLE).get(uuid).run(conn, AnnounceProfile.class);
        return data;
    }

}
