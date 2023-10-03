import './App.scss';
import Palette from "./pages/palette";

function App() {
  return (
    <div className="App">
      <header className="App-header">
          <p>
              <code>Color Palette</code>
          </p>
        <Palette></Palette>
      </header>
    </div>
  );
}

export default App;
