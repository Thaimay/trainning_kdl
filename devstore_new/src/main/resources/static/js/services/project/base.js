'use strict';

/*
 * Contains a service to communicate with API
 */
angular
    .module('app.services')
    .factory('ProjectBaseService', ProjectBaseService);

function ProjectBaseService($http, BaseService) {
    var service = angular.copy(BaseService);
    function findProjectActionStatus(cId, sId) {
        if (cId && sId) {
            return service.makeGetRequest('sp/project/find/action/status/' + cId + "/" + sId, {});
        } else {
            return service.makeGetRequest('sp/project/find/action/status/0/0', {});
        }
    }

    function findProjectSwitingItemControl(projectCategoryId) {
    	if (projectCategoryId) {
    		return service.makeGetRequest('sp/projectswiting/category/' + projectCategoryId, {});
    	} else {
    		return service.makeGetRequest('sp/projectswiting/category/' + 1, {});
    	}
    }

    function findProjectSchedule(projectId) {
    	return service.makeGetRequest('sp/project/find/project_schedule/' + projectId, {});
    }

    function findInitProjectSchedule(categoryId, channelId, year, month, day) {
        month = parseInt(month);
        day = parseInt(day);
        return service.makeGetRequest('sp/project/find/project_schedule_list/' + categoryId + "/" + channelId + "/" + year + "/" + month + "/" + day, {});
    }

    function findInitSchedule(categoryId, channelId) {
    	if (!categoryId || !channelId) {
    		return null;
    	}
    	return service.makeGetRequest('sp/project/find/project_schedule_list/' + categoryId + "/" + channelId, {});
    }

    function actionStatus(data) {
      return service.makePostRequest('sp/project/action/status', data);
    }

    return {
    	actionStatus: actionStatus,
    	findProjectSwitingItemControl: findProjectSwitingItemControl,
    	findProjectSchedule: findProjectSchedule,
    	findProjectActionStatus: findProjectActionStatus,
    	findInitProjectSchedule: findInitProjectSchedule,
    	findInitSchedule: findInitSchedule
    };
};
