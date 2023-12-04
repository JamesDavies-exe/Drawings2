
function borrarImagen(){
    console.log( "Enviando " + document.getElementById("imageId").value)
    fetch('http://localhost:8080/moveToBin', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
    },
      body: JSON.stringify(document.getElementById("imageId").value)
  })
  .then(response => {
      if (!response.ok) {
          throw new Error('Error al enviar dato');
      }
      return response.json();
  })
  .then(data => {
      console.log(data); // Manejar la respuesta del servidor si es necesario
  })
  .catch(error => {
      console.error('Error:', error);
  });
  document.location.reload();
}

function cambiarPrivacidad(){
    console.log( "Enviando " + document.getElementById("imageId").value)
    fetch('http://localhost:8080/changePublicity', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
    },
      body: JSON.stringify(document.getElementById("imageId").value)
  })
  .then(response => {
      if (!response.ok) {
          throw new Error('Error al enviar dato');
      }
      return response.json();
  })
  .then(data => {
      console.log(data); // Manejar la respuesta del servidor si es necesario
  })
  .catch(error => {
      console.error('Error:', error);
  });
}