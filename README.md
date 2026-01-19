<div align="center">
  <h1>🍕 Picerija - Java picas pasūtījumu sistēma</h1>
  <p><strong>Ātra, saprotama pasūtījumu sistēma.</strong></p>
  <p>
    <img src="picerijaimg.png" width="680" alt="Picerija sistēmas ekrānattēls">
  </p>
  <p>
    <em>Pasūtījums ienāk → tiek apstrādāts secīgi → tiek nodots klientam.</em>
  </p>
</div>

<hr>

<h2> Projekta ideja</h2>
<p>
  Šis projekts ir Java programma, kas imitē picas pasūtījumu apkalpošanu picērijā.
  Pasūtījumi tiek apstrādāti secīgi - pirmais ienāca, pirmais tiek apkalpots.
</p>

<h2> Mērķis</h2>
<p>
  Izveidot vienkāršu, bet pilnībā funkcionālu sistēmu, kas reālajā vidē palīdzētu
  klientu un pasūtījumu apkalpošanai.
</p>

<hr>

<h2> Kā sistēma darbojas</h2>
<ol>
  <li>Klients ievada datus un izvēlas pasūtījumu.</li>
  <li>Sistēma aprēķina cenu un ieliek pasūtījumu rindā.</li>
  <li>Pasūtījums pāriet pa statusiem: <strong>PIEŅEMTS → GATAVOJAS → GATAVS → NODOTS</strong>.</li>
  <li>Pasūtījumi tiek saglabāti un vajadzības gadījumā ielādēti no faila.</li>
</ol>

<hr>

<h2>🟢 Funkcionalitāte</h2>
<table>
  <thead>
    <tr>
      <th align="left">Funkcija</th>
      <th align="left">Apraksts</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Klienta dati</td>
      <td>Vārds, tālrunis un piegādes veids.</td>
    </tr>
    <tr>
      <td>Picas izvēle</td>
      <td>Saraksts ar pieejamajām picām un izmēriem (S / M / L).</td>
    </tr>
    <tr>
      <td>Piedevas</td>
      <td>Mērces un papildu sastāvdaļas pēc izvēles.</td>
    </tr>
    <tr>
      <td>Vairākas picas</td>
      <td>Vienam pasūtījumam var pievienot vairākas picas.</td>
    </tr>
    <tr>
      <td>Cenas aprēķins</td>
      <td>Automātiska kopējās cenas noteikšana.</td>
    </tr>
    <tr>
      <td>Pasūtījumu rinda</td>
      <td>FIFO secība, lai viss notiek loģiski un saprotami.</td>
    </tr>
    <tr>
      <td>Statusi</td>
      <td>PIEŅEMTS, GATAVOJAS, GATAVS, NODOTS.</td>
    </tr>
    <tr>
      <td>Failu saglabāšana</td>
      <td>Pasūtījumu saglabāšana un ielāde no faila.</td>
    </tr>
  </tbody>
</table>

<hr>

<h2>🟠 Papildfunkcionalitāte (idejas attīstībai)</h2>
<ul>
  <li> Mūzika un skaņas darbībām (picas pievienošana, fona mūzika)</li>
  <li> Grafika un detalizētāks vizuālais interfeiss</li>
  <li> Taimeris picas izgatavošanas laikam</li>
</ul>

<hr>

<h2>🧰 Izmantotās tehnoloģijas</h2>
<ul>
  <li>Java</li>
  <li>Java Collections Framework (Queue, List)</li>
  <li>JOptionPane</li>
  <li>Failu ievade / izvade (FileWriter, FileReader)</li>
</ul>

<hr>

<h2>📌 Statusa leģenda</h2>
<ul>
  <li>🟢 Pabeigts</li>
  <li>🟡 Daļēji pabeigts</li>
  <li>🟠 Izstrādes procesā</li>
  <li>🔴 Neiesākts</li>
</ul>
