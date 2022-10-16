const toast = Swal.mixin({
    toast: true,
    position: 'top-end',
    padding: '1em',
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true,
    didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
    }
})

Toast = {
    success: function (message) {
        toast.fire({
            icon: 'success',
            title: message
        })
    },
    error: function (message) {
        toast.fire({
            icon: 'error',
            title: message
        })
    },
    warning: function (message) {
        toast.fire({
            icon: 'warning',
            title: message
        })
    }
}