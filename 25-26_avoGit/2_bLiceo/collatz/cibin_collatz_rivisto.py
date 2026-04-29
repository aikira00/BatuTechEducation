# // Daniel Shiffman
# // https://thecodingtrain.com/CodingInTheCabana/002-collatz-conjecture.html
# // https://youtu.be/EYLWxwo1Ed8
# // https://editor.p5js.org/codingtrain/sketches/XjLDE7gu6

import matplotlib.pyplot as plt
import numpy as np

MAX_NUM = 10000
ANGLE = 0.15
LENGTH = 5
plt.figure(figsize=(10, 10), facecolor='black')
#     let len = 5;
#     let angle = 0.15;
#     resetMatrix();
#     translate(width/2, height);
# function setup() {
#   createCanvas(800, 600);
#   background(0);
#   for (let i = 1; i < 10000; i++) {
#     let sequence = [];
#     let n = i;
#     do {
#       sequence.push(n);
#       n = collatz(n);
#     } while (n != 1);
#     sequence.push(1);
#     sequence.reverse();
#
for n in range(1, MAX_NUM + 1):
    # reset lista per sequenza
    seq = [n]
    while seq[-1] != 1:
        ultimo = seq[-1]
        # si potrebbe dividere (3*ultimo+1)/2
        seq.append(ultimo // 2 if ultimo % 2 == 0 else 3 * ultimo + 1)
    seq.reverse()

    x, y = [0], [0]
    direzione = np.pi / 2
    for i in range(len(seq) - 1):
        # Se cresce gira a destra, se cala gira a sinistra
        direzione += ANGLE if seq[i + 1] > seq[i] else -ANGLE
        # if seq[i] % 2 ==0:
        #     direzione += ANGLE
        # else:
        #     direzione -= ANGLE
        x.append(x[-1] + np.cos(direzione) * LENGTH)
        y.append(y[-1] + np.sin(direzione) * LENGTH)

    # plotto ogni sequenza
    plt.plot(x, y, color='green', linewidth=0.1, alpha=0.3)

plt.show()
#     for (let j = 0; j < sequence.length; j++) {
#       let value = sequence[j];
#       if (value % 2 == 0) {
#         rotate(angle);
#       } else {
#         rotate(-angle);
#       }
#       strokeWeight(2);
#       stroke(255, 10);
#       line(0, 0, 0, -len);
#       translate(0, -len);
#     }
#
#     // Visualize the list
#   }
# }
#
#
#
#
# function collatz(n) {
#   // even
#   if (n % 2 == 0) {
#     return n / 2;
#     // odd
#   } else {
#     return (n * 3 + 1)/2;
#   }
# }
