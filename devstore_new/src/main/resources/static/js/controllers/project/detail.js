angular
.module('app.core')
.controller('ProjectDetailController', function (ProjectBaseService, $scope, $filter, $timeout) {
  var vm = this;
  vm.projectId = null;
  vm.projectCategoryId = null;

  Window.gvm = vm;

  vm.initData = function () {
	//vm.projectId = $("#project_edit_project_id").val();
    //vm.mProjectActionStatusId = $("#project_edit_m_project_action_status_id").val();
  }
});
