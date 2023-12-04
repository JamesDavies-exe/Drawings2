const canvas = document.getElementById("myCanvas");
const ctx = canvas.getContext("2d");
const tipo = document.getElementById("select");

//Cogemos el tipo de figura que queremos dibujar
function getType() {
  return document.getElementById("select").value;
}

//Declaramos el Array de figuras
let drawArray = [];



canvas.addEventListener("click", function (event) {
  const rect = canvas.getBoundingClientRect();
  const x = event.clientX - rect.left;
  const y = event.clientY - rect.top;
  let shape = getType();
  let color = document.getElementById("style1").value;
  let size = document.getElementById("range1").value;
  let fill = document.getElementById("fill").checked;
  processFigure(shape, x, y, color, size, fill);

  console.log(drawArray);

  let jsonInfo = JSON.stringify(drawArray);

  document.getElementById("jsonInfo").value = jsonInfo;
  console.log(document.getElementById("jsonInfo").value)
});

export function processFigure(shape, x, y, color, size, fill) {
  let id = 0;
  switch (shape) {
    case "circulo":
      let circleObject = {
        type: "circulo",
        coordX: x,
        coordY: y,
        size: parseInt(size),
        color: color,
        fill: fill,
        id: id++
      };
      drawArray.push(circleObject);
      drawCircle(x, y, color, size, fill);

      break;
    case "cuadrado":
      let squareObject = {
        type: "cuadrado",
        coordX: x,
        coordY: y,
        size: parseInt(size),
        color: color,
        fill: fill,
        id: id++
      };
      drawArray.push(squareObject);
      drawSquare(x, y, color, size, fill);
      break;
    case "triangulo":
      let triangleObject = {
        type: "triangulo",
        coordX: x,
        coordY: y,
        size: parseInt(size),
        color: color,
        fill: fill,
        id: id++
      };
      drawArray.push(triangleObject);
      drawTriangle(x, y, color, size, fill);
      break;
    case "estrella":
      let starObject = {
        type: "estrella",
        coordX: x,
        coordY: y,
        size: parseInt(size),
        color: color,
        fill: fill,
        id: id++
      };
      drawArray.push(starObject);
      drawStar(x, y, color, size, fill);
      break;

    default:
  }
  id++;
}

function drawCircle(x, y, color, size, fill) {
  console.log("Dibujando un circulo");
  ctx.beginPath();
  ctx.arc(x, y, size / 2, 0, 2 * Math.PI);
  if (fill == true) {
    ctx.fillStyle = color;
    ctx.fill();
  }
  ctx.lineWidth = 3;
  ctx.strokeStyle = color;
  ctx.stroke();

  ctx.closePath();

  var figuresList = document.getElementById("list");
  var li = document.createElement("li");
  li.textContent = "Circulo";
  figuresList.appendChild(li);
}
function drawSquare(x, y, color, size, fill) {
  console.log("Dibujando un cuadrado");
  let radius = size / 2;
  ctx.beginPath();
  ctx.rect(x - radius, y - radius, size, size);
  ctx.lineWidth = 3;
  ctx.strokeStyle = color;
  ctx.stroke();
  if (fill == true || fill == true) {
    ctx.fillStyle = color;
    ctx.fill();
  }

  var figuresList = document.getElementById("list");
  var li = document.createElement("li");
  li.textContent = "Cuadrado";
  figuresList.appendChild(li);
}
function drawTriangle(x, y, color, size, fill) {
  let center = (Math.sqrt(3) * Number(size)) / 2;
  let height = (Math.sqrt(3) * size) / 2;
  x -= height / 2;
  y += height / 2;
  ctx.beginPath();
  ctx.moveTo(Number(x), Number(y));
  ctx.lineTo(Number(x) + Number(size), Number(y));
  ctx.lineTo(Number(x) + Number(size) / 2, Number(y) - center);
  ctx.lineTo(Number(x) + Number(size) / 2, Number(y) - center);
  ctx.lineTo(Number(x), Number(y));
  if (fill == true || fill == true) {
    ctx.fillStyle = color;
    ctx.fill();
  }
  ctx.closePath();
  ctx.lineWidth = 3;
  ctx.strokeStyle = color;
  ctx.stroke();

  var figuresList = document.getElementById("list");
  var li = document.createElement("li");
  li.textContent = "Triangulo";
  figuresList.appendChild(li);
}
function drawStar(x, y, color, size, fill) {
  let outerRadius = size / 2;
  let innerRadius = outerRadius / 4;
  let rotAngle = (Math.PI / 2) * 3;
  let step = Math.PI / 7;
  let newX = x;
  let newY = y;

  ctx.beginPath();
  ctx.moveTo(x, y - outerRadius);
  for (let i = 0; i < 7; i++) {
    newX = x + Math.cos(rotAngle) * outerRadius;
    newY = y + Math.sin(rotAngle) * outerRadius;
    ctx.lineTo(newX, newY);
    rotAngle += step;

    newX = x + Math.cos(rotAngle) * innerRadius;
    newY = y + Math.sin(rotAngle) * innerRadius;
    ctx.lineTo(newX, newY);
    rotAngle += step;
  }
  ctx.lineTo(x, y - outerRadius);
  ctx.closePath();
  ctx.lineWidth = 3;
  ctx.strokeStyle = color;
  ctx.stroke();
  if (fill == true || fill == true) {
    ctx.fillStyle = color;
    ctx.fill();
  }

  var figuresList = document.getElementById("list");
  var li = document.createElement("li");
  li.textContent = "Estrella";
  figuresList.appendChild(li);
}

function clearCanvas(){
  ctx.clearRect(0, 0, canvas.width, canvas.height);
}

document.getElementById("formSubmit").onclick = function enviarDato() {
  console.log("Enviando " + drawArray);

  fetch('http://localhost:8080/paint', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json',
      },
      body: JSON.stringify(drawArray),
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
