
'use strict';

$(() => {
  let timeStart = performance.now();
  const numRects = 1E9;
  const width = 1 / numRects;
  const numWorkers = 8;
  const chunkSize = numRects / numWorkers;
  let sum = 0;
  let finalizedWorkers = 0;

  for (let i = 0; i < numWorkers; i++) {
    let w = new Worker('pi_worker.js');
    w.postMessage({
      numRects: numRects,
      start: i * chunkSize,
      end: (i + 1) * chunkSize
    });
    w.onmessage = (event) => {
      finalizedWorkers++;
      sum += event.data;
      if (finalizedWorkers === numWorkers) {
        let area = width * sum;
        let timeEnd = performance.now();
        $('#resultado').html(
          'Valor de pi = ' + area + '<br \>' +
          'Tiempo = ' + (timeEnd - timeStart) / 1000
        );
      }
    };
  }
});
