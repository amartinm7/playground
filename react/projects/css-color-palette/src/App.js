import './App.scss';

function App() {
  return (
    <div className="App">

      <div className="main-content">
        <div className="palette-grid">
          <div className="palette-headers">
            <h1>Primary Colors</h1>
            <h2>Main colors of the site. We're making it shine ☀️</h2>
          </div>
          <div className="palette palette--main-color">
            <div className="palette__colors">
              <div className="palette__color" style={{backgroundColor:'#FFD151'}}></div>
              <div className="palette__shades">
                <div className="palette__shades__item" style={{backgroundColor: '#FFEAAF'}}>#FFEAAF</div>
                <div className="palette__shades__item" style={{backgroundColor: '#FFE190'}}>#FFE190</div>
                <div className="palette__shades__item" style={{backgroundColor: '#FFD970'}}>#FFD970</div>
              </div>
            </div>
            <div className="palette__info">
              <div className="palette__info__name">Mustard</div>
              <div className="palette__info__hexcode">#FFD151</div>
            </div>
          </div>
          <div className="palette palette--main-color">
            <div className="palette__colors">
              <div className="palette__color" style={{backgroundColor: '#FFAE03'}}></div>
              <div className="palette__shades">
                <div className="palette__shades__item" style={{backgroundColor: '#FFD275'}}>#FFD275</div>
                <div className="palette__shades__item" style={{backgroundColor: '#FFC447'}}>#FFC447</div>
                <div className="palette__shades__item" style={{backgroundColor: '#FFB519'}}>#FFB519</div>
              </div>
            </div>
            <div className="palette__info">
              <div className="palette__info__name">UCLA Gold</div>
              <div className="palette__info__hexcode">#FFAE03</div>
            </div>
          </div>
          <div className="palette-headers">
            <h1>Secondary Colors</h1>
            <h2>Remaining colors that make up the palette. Also useful for status/info alerts.</h2>
          </div>
          <div className="palette palette--secondary-color">
            <div className="palette__colors">
              <div className="palette__color" style={{backgroundColor: '#20A39E'}}></div>
              <div className="palette__shades">
                <div className="palette__shades__item" style={{backgroundColor: '#71C4C1'}}>#71C4C1</div>
                <div className="palette__shades__item" style={{backgroundColor: '#48B3AF'}}>#48B3AF</div>
              </div>
            </div>
            <div className="palette__info">
              <div className="palette__info__name">Light Sea Green</div>
              <div className="palette__info__hexcode">#20A39E</div>
            </div>
          </div>
          <div className="palette palette--secondary-color">
            <div className="palette__colors">
              <div className="palette__color" style={{backgroundColor: '#EF5B5B'}}></div>
              <div className="palette__shades">
                <div className="palette__shades__item" style={{backgroundColor: '#F49696'}}>#F49696</div>
                <div className="palette__shades__item" style={{backgroundColor: '#F17878'}}>#F17878</div>
              </div>
            </div>
            <div className="palette__info">
              <div className="palette__info__name">Sunset Orange</div>
              <div className="palette__info__hexcode">#EF5B5B</div>
            </div>
          </div>
          <div className="palette palette--secondary-color">
            <div className="palette__colors">
              <div className="palette__color" style={{backgroundColor: '#083D77'}}></div>
              <div className="palette__shades">
                <div className="palette__shades__item font-white" style={{backgroundColor: '#6183A8'}}>#6183A8</div>
                <div className="palette__shades__item font-white" style={{backgroundColor: '#34608F'}}>#34608F</div>
              </div>
            </div>
            <div className="palette__info">
              <div className="palette__info__name">Dark Cerulean</div>
              <div className="palette__info__hexcode">#083D77</div>
            </div>
          </div>
          <div className="palette-headers">
            <h1>Many Shades of Gray</h1>
            <h2>Let's try to keep the number of grays below 50.</h2>
          </div>
          <div className="palette palette--grays">
            <div className="palette__color" style={{backgroundColor: '#E8E9E9'}}></div>
            <div className="palette__info">
              <div className="palette__info__name">Platinum</div>
              <div className="palette__info__hexcode">#E8E9E9</div>
            </div>
          </div>
          <div className="palette palette--grays">
            <div className="palette__color" style={{backgroundColor: '#D1D3D4'}}></div>
            <div className="palette__info">
              <div className="palette__info__name">Light Gray</div>
              <div className="palette__info__hexcode">#D1D3D4</div>
            </div>
          </div>
          <div className="palette palette--grays">
            <div className="palette__color" style={{backgroundColor: '#BABDBF'}}></div>
            <div className="palette__info">
              <div className="palette__info__name">Gray</div>
              <div className="palette__info__hexcode">#BABDBF</div>
            </div>
          </div>
          <div className="palette palette--grays">
            <div className="palette__color" style={{backgroundColor: '#808488'}}></div>
            <div className="palette__info">
              <div className="palette__info__name">Old Silver</div>
              <div className="palette__info__hexcode">#808488</div>
            </div>
          </div>
          <div className="palette palette--grays">
            <div className="palette__color" style={{backgroundColor: '#666A6D'}}></div>
            <div className="palette__info">
              <div className="palette__info__name">Dim Gray</div>
              <div className="palette__info__hexcode">#666A6D</div>
            </div>
          </div>
          <div className="palette palette--grays">
            <div className="palette__color" style={{backgroundColor: '#4D5052'}}></div>
            <div className="palette__info">
              <div className="palette__info__name">Feldgrau</div>
              <div className="palette__info__hexcode">#4D5052</div>
            </div>
          </div>
          <div className="palette palette--grays">
            <div className="palette__color" style={{backgroundColor: '#333537'}}></div>
            <div className="palette__info">
              <div className="palette__info__name">Jet</div>
              <div className="palette__info__hexcode">#333537</div>
            </div>
          </div>
          <div className="palette palette--grays">
            <div className="palette__color" style={{backgroundColor: '#1C1D1E'}}></div>
            <div class="palette__info">
              <div class="palette__info__name">Eerie Black</div>
              <div class="palette__info__hexcode">#1C1D1E</div>
            </div>
          </div>
        </div>
      </div>
      <aside class="context">
        <div class="explanation">Hover over primary & secondary colors for additional shades.</div>
      </aside>
      <footer><i class="icon-social-twitter icons"></i><i class="icon-social-github icons"></i><i class="icon-social-dribbble icons"></i></footer>
    </div>
  );
}

export default App;
