package uk.co.wowcher.threejava.ratpack.model.model;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.util.StringMapper;
import uk.co.wowcher.threejava.ratpack.App;

import javax.sql.DataSource;
import java.util.List;

/**
 * TODO make immutable
 * */
public class DealsListing {

    /* See DealVoucherSite.hbm.xml*/

    private static final String SITE_PATH = ":sitePath";

    private static final String GET_DEALS_BY_LOCATION =
            "select dv.id, PRODUCT.title, dv.deal_product" +
                    "from DEAL_VOUCHER dv\n" +
                    "left join DEAL_VOUCHER_SITE dvs on dvs.deal_voucher_id = dv.id\n" +
                    "    left join PRODUCT on PRODUCT.id = dv.id\n" +
                    "    left join SITE on site.id = dvs.site_id\n" +
                    "where site.site_path = " + SITE_PATH +
                    "and dv.start_date <= sysdate" +
                    "and dv.closing_date >= sysdate" +
                    "and PRODUCT.status_id = 1" +
//                    "and dvs.bonus = 'N'" +
                    "and dv.always_on = 'N'" +
                    "order by dv.start_date desc;";

    public ListDealsOptions filter;
    public int total;
    public int totalPages;
    public final List<Deal> deals;

    public DealsListing(List<Deal> deals, int totalPages, int total, ListDealsOptions filter) {
        this.deals = deals;
        this.totalPages = totalPages;
        this.total = total;
        this.filter = filter;
    }

    public DealsListing(List<Deal> deals) {
        this.deals = deals;
    }

    public static DealsListing getByLocation(String location) {

        DBI dbi = new DBI(App.dataSource);
        Handle h = dbi.open();
        h.createQuery(GET_DEALS_BY_LOCATION)
        .bind(SITE_PATH, location);

        h.close();
    }
}
