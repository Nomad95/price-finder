package pl.igor.pricefinder.search.pricefindersearch;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SearchSiteTask implements Runnable {

    //TODO: moze dodac ldawanie itemow do koszyka?

    @Override
    public void run() {
        //get site metadata (where prices are etc)
        //search page by page (various site types)?
        //store items
        //how much sites per search?
        //filter items??
    }
}
