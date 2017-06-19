import play.filters.cors.CORSFilter;
import play.http.DefaultHttpFilters;

import javax.inject.Inject;

/**
 * Created by yenny on 12/14/16.
 */
public class Filters extends DefaultHttpFilters {
    @Inject
    public Filters(CORSFilter corsFilter) {
        super(corsFilter);
    }
}
