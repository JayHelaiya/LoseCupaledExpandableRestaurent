package com.example.nichetech.expandablelistexample;

import com.example.nichetech.expandablelistexample.object.Restaurant;
import com.example.nichetech.expandablelistexample.object.resp.RespAdvanceSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {


    public static HashMap<String, List<? extends Restaurant>> getData(List<RespAdvanceSearch.WCFGetDetailForAdvancedSearch.Area> areas, List<RespAdvanceSearch.WCFGetDetailForAdvancedSearch.Cuisine> cuisines, List<RespAdvanceSearch.WCFGetDetailForAdvancedSearch.SpecialOffer> specialOffers) {

        HashMap<String, List<? extends Restaurant>> expandableListDetail = new HashMap<String, List<? extends Restaurant>>();



        expandableListDetail.put("areas", areas);
        expandableListDetail.put("cuisines", cuisines);
        expandableListDetail.put("specialOffers", specialOffers);
        return expandableListDetail;
    }


}
