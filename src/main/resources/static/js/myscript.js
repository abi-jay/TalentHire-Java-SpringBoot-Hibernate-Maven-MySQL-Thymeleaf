ClassicEditor
    .create( document.querySelector( '#about' ) )
    .then( editor => {
            console.log( editor );
    } )
    .catch( error => {
            console.error( error );
    } );