/*
 * Contains a base service
 */


angular
    .module('app.services')
    // .constant('BASE_URL', "http://192.168.1.14:8080/")
     .constant('BASE_URL', "/wsd/")
    //.constant('BASE_URL', 'http://192.168.1.18:8080/')
    //.constant('BASE_URL', 'http://13.113.119.106/')
    //.constant('BASE_URL', 'http://192.168.1.3:8080/')
    .factory('BaseService', baseService);

function baseService($http,$filter, BASE_URL) {
    var base = {
        'makeGetRequest': makeGetRequest,
        'makePostRequest': makePostRequest,
        'makeUploadRequest': makeUploadRequest,
        'getOrElse': getOrElse,
        'copyObject': copyObject,
        'getDatetime': getDatetime,
        'formatDate' : formatDate,
        'getTermByYearMonth' : getTermByYearMonth,
        'getDiffByDayHour' : getDiffByDayHour,
        'getListAccount' : getListAccount,
        'getListCorporation' : getListCorporation,
        'getListCorporationGroup' : getListCorporationGroup,
        'getListTenant' : getListTenant,
        'getListKeyman' : getListKeyman,
        'getListBrand' : getListBrand,
        'getListBuildingPrefectures' : getListBuildingPrefectures,
        'getListSalesAgent' : getListSalesAgent,
        'getListParticipatingStore' : getListParticipatingStore,
        'getDatetime': getDatetime,
        'baseUrl': baseUrl,
        'get': get,
        'post': post,
        'suggestPost': suggestPost,
        'postFile': postFile,
        'convertDataImage': convertDataImage
    };

    function baseUrl() {
        return BASE_URL;
    }

    function getListAccount(){
        return this.makeGetRequest('sp/account/list', {}).then(function(data) {
            return data;
        });
    }

    function getListCorporation(){
        return this.makeGetRequest('sp/corporation/list', {}).then(function(data) {
            return data;
        });
    }

    function getListCorporationGroup(){
        return this.makeGetRequest('sp/corporation_group/list', {}).then(function(data) {
            return data;
        });
    }

    function getListTenant(){
        return this.makeGetRequest('sp/tenant/list', {}).then(function(data) {
            return data;
        });
    }

    function getListKeyman(){
        return this.makeGetRequest('sp/keyman/list', {}).then(function(data) {
            return data;
        });
    }

    function getListBrand(){
        return this.makeGetRequest('sp/brand/list', {}).then(function(data) {
            return data;
        });
    }

    function getListBuildingPrefectures(){
        return this.makeGetRequest('sp/building_prefectures/list', {}).then(function(data) {
            return data;
        });
    }

    function getListSalesAgent(){
        return this.makeGetRequest('sp/sales_agent/list', {}).then(function(data) {
            return data;
        });
    }

    function getListParticipatingStore(){
        return this.makeGetRequest('sp/participating_store/list', {}).then(function(data) {
            return data;
        });
    }

    function get(url, params) {
        return makeGetRequest(url, params);
    }

    function post(url, params) {
        return makePostRequest(url, params);
    }

    function suggestPost(url, params) {
        return makeSuggestRequest(url, params);
    }

    function postFile(url, params) {
        return makeUploadRequest(url, params);
    }

    function makeGetRequest(url, params) {
        var requestUrl = _makeGetParameterUrl(BASE_URL + url, params)

        return request({
            'url': requestUrl,
            'method': 'GET',
            'headers': {
                'Content-Type': 'application/json'
            },
            'cache': false,
            'timeout': 30000
        });
    }

    function makePostRequest(url, data) {
        var requestUrl = BASE_URL +  url;

        return request({
            'url': requestUrl,
            'method': 'POST',
            'headers': {
                'Content-Type': 'application/json'
            },
            'data': data,
            'timeout': 30000
        });
    }

    function makeSuggestRequest(url, data) {
        var requestUrl = BASE_URL +  url;

        return suggestRequest({
            'url': requestUrl,
            'method': 'POST',
            'headers': {
                'Content-Type': 'application/json'
            },
            'data': data,
            'timeout': 30000
        });
    }

    function makeUploadRequest(url, data) {
        var requestUrl = BASE_URL +  url;

        return request({
            'url': requestUrl,
            'method': 'POST',
            headers : {
                'Content-Type': undefined
            },
            enctype: 'multipart/form-data',
            'data': data,
            'timeout': 30000
        });
    }

    function suggestRequest(config) {
        Spinner().show();

        return $http(config).then(function (response) {
        	setTimeout(function() {
        		Spinner().hide();
        	}, 400);
            return response.data;
        }).catch(function() {
        	Spinner().hide();
        });
    }

    function request(config) {
        Spinner().show();

        return $http(config).then(function (response) {
        	setTimeout(function() {
        		Spinner().hide();
        	}, 400);
            return response.data;
        }).catch(dataServiceError);
    }

    function getOrElse(value, defaultValue) {
        if (value === undefined) {
            return defaultValue;
        } else {
            return value;
        }
    }

    function copyObject(origin, target) {
        Object.keys(origin).forEach(function(key) {
           target[key] = origin[key];
        });
        return target;
    }

    function getDatetime(datepicker) {
        return datepicker.datepicker({ dateFormat: 'yy-mm-dd' }).val();
    }

    function _makeGetParameterUrl(url, params) {
        if (params === undefined || params === [] || params === {}) {
            return url;
        } else {
            var list = Object.keys(params).map(function(key, index) {
                var symbol = '&'
                if (index === 0) symbol = '?';
                return symbol + key + '=' + params[key];
            });
            return url + list.join("");
        }
    }

    // convert LocalDateTime to string
    function formatDate(value, format){
        let dateObj = new Date(value);
        return $filter('date')(dateObj, format)
    }

    // get data base code or link path
    function convertDataImage(file) {
        if(file.dataFile != null) {
            data = file.dataFile;
            if(data.indexOf("data:image/") > -1)
            {
                return data;
            }
        }
        else {
            return BASE_URL + file.path;
        }
    }
    // get term unit based on years and months
    function getTermByYearMonth(start, end) {
        let startDate = new Date(start);
        let endDate = new Date(end);
        let startYear = startDate.getFullYear();
        let endYear = endDate.getFullYear();
        let monthStart = startDate.getMonth();
        let monthEnd = endDate.getMonth();
        if (monthStart === 0) {
            monthStart++;
            monthEnd++;
        }
        let months = (endYear - startYear) * 12 + (monthEnd - monthStart) + 1;
        let numberOfYears = Math.floor(months / 12);
        let numberOfMonths = months - (numberOfYears * 12);
        return numberOfYears + '年間' + numberOfMonths + '月';
    };

    // get number of days and hours from specific date to now
    function getDiffByDayHour(specificDate) {
        let dateValue = new Date(specificDate);
        let now = new Date();
        let diff = (now.getTime() - dateValue.getTime()) / 1000;
        diff /= (60 * 60);
        let hours = Math.abs(Math.round(diff));
        let numberOfDays = Math.floor(hours/24);
        let numberOfHours = hours - numberOfDays*24;
        return numberOfDays + '日' + numberOfHours + '時間前';
    };

    return base;

    function dataServiceError(errorResponse) {
        alert("通信エラー");
        Spinner().hide();
        console.log('XHR Failed ');
        console.log(errorResponse);
        return errorResponse;
    }
}
