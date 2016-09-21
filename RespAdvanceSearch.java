package com.example.nichetech.expandablelistexample.object.resp;

import com.example.nichetech.expandablelistexample.object.Restaurant;

import java.util.List;

/**
 * Created by nichetech on 20/9/16.
 */
public class RespAdvanceSearch {

    public final WCFGetDetailForAdvancedSearch WCFGetDetailForAdvancedSearch;

    public RespAdvanceSearch(RespAdvanceSearch.WCFGetDetailForAdvancedSearch wcfGetDetailForAdvancedSearch) {
        WCFGetDetailForAdvancedSearch = wcfGetDetailForAdvancedSearch;
    }

    public class WCFGetDetailForAdvancedSearch{

        public final int ErrorCode;
        public final String ErrorMessage;
        public final String ErrorMessageAR;
        public final String ServiceName;
        public final List<Area> listArea;
        public final List<Cuisine> listCuisine;
        public final List<SpecialOffer> listSpecialOffer;

        public WCFGetDetailForAdvancedSearch(int errorCode, String errorMessage, String errorMessageAR, String serviceName, List<Area> listArea, List<Cuisine> listCuisine, List<SpecialOffer> listSpecialOffer) {
            ErrorCode = errorCode;
            ErrorMessage = errorMessage;
            ErrorMessageAR = errorMessageAR;
            ServiceName = serviceName;
            this.listArea = listArea;
            this.listCuisine = listCuisine;
            this.listSpecialOffer = listSpecialOffer;
        }

        public int getErrorCode() {
            return ErrorCode;
        }

        public String getErrorMessage() {
            return ErrorMessage;
        }

        public String getErrorMessageAR() {
            return ErrorMessageAR;
        }

        public String getServiceName() {
            return ServiceName;
        }

        public List<Area> getListArea() {
            return listArea;
        }

        public List<Cuisine> getListCuisine() {
            return listCuisine;
        }

        public List<SpecialOffer> getListSpecialOffer() {
            return listSpecialOffer;
        }

        public final class Area extends Restaurant{

            public final int AreaID;
            public final String AreaName;

            public Area(int areaID, String areaName) {
                AreaID = areaID;
                AreaName = areaName;
            }

            public int getAreaID() {
                return AreaID;
            }

            public String getAreaName() {
                return AreaName;
            }
        }

        public final class Cuisine extends Restaurant{

            public final int CuisineID;
            public final String CuisineName;

            public Cuisine(int cuisineID, String cuisineName) {
                CuisineID = cuisineID;
                CuisineName = cuisineName;
            }

            public int getCuisineID() {
                return CuisineID;
            }

            public String getCuisineName() {
                return CuisineName;
            }
        }

        public  final class SpecialOffer extends Restaurant{

            public final int OfferID;
            public final String OfferName;

            public SpecialOffer(int offerID, String offerName) {
                OfferID = offerID;
                OfferName = offerName;
            }

            public int getOfferID() {
                return OfferID;
            }

            public String getOfferName() {
                return OfferName;
            }
        }


    }
}
