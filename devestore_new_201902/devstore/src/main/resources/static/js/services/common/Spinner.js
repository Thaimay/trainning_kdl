'use strict';

var Spinner = function (isShow, spinnerElement) {
    if (!(this instanceof Spinner)) {
        return new Spinner(isShow, spinnerElement);
    }

    this.spinnerElement = document.getElementById('spinner');
}

Spinner.prototype.show = function () {
    if (this.show === true) return;

    this.spinnerElement.classList.add('isShow');
    this.isShow = true;
}

Spinner.prototype.hide = function () {
    if (this.show === false) return;

    this.isShow = false;
    this.spinnerElement.classList.remove('isShow');
}
