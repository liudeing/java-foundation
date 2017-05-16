package concurrent.ConcurrentSearch;

import java.util.concurrent.Callable;

/**
 * Created by liur on 17-5-2.
 */
public class SearchTask implements Callable {
    int begin,end,searchValue;
    public SearchTask(int searchValue,int begin,int end){
        this.searchValue=searchValue;
        this.begin=begin;
        this.end=end;
    }

    @Override
    public Integer call() throws Exception {
        int re = ArraySearch.search(searchValue,begin,end);
        return re;
    }
}
