package com.example.nichetech.expandablelistexample.object.req;

/**
 * Created by nichetech on 20/9/16.
 */
public class ReqAdvanceSearch {

    public final ObjWCFGetSearch objWCFGetSearch;

    public ReqAdvanceSearch(ObjWCFGetSearch objWCFGetSearch) {
        this.objWCFGetSearch = objWCFGetSearch;
    }
    public static final class  ObjWCFGetSearch{

        public final int CountryID;

        public ObjWCFGetSearch(int countryID) {
            CountryID = countryID;
        }
        public int getCountryID() {
            return CountryID;
        }
    }

}

