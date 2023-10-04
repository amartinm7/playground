import './index.scss';

function Palette() {

    const itemProps = (label, func) => Object.assign([], {label}, {func})
    const decToHex = (dec) => (dec + Math.pow(16, 6)).toString(16).substr(-6)

    const getFFFFF = () => parseInt("FFFFFF", 16)
    const calcHexColor2 = (index) =>  {
        const result = (getFFFFF() - (16 * index)).toString(16)
        console.log(`rgb(${result.slice(0,2)}, ${result.slice(2,4)}, ${result.slice(4,6)})`)
        // console.log(`rgb(${parseInt(result.slice(0,2),16)}, ${parseInt(result.slice(2,4),16)}, ${parseInt(result.slice(4,6),16)})`)
        return `rgb(${parseInt(result.slice(0,2),16)}, ${parseInt(result.slice(2,4),16)}, ${parseInt(result.slice(4,6),16)})`
    }

    const calcHexColor = (index) => {
        const columnH = (Math.trunc(index % 20) + 1) * 360 / 20 //increase
        const columnS = 100 - ((index % 20) * 100 / 40) // decrease
        const columnL = ((index % 20) * 100 / 40) // increase
        console.log(`hsl(${columnH}, ${columnS}%, ${columnL}%)`)
        return `hsl(${columnH}, ${columnS}%, ${columnL}%)`
    }

    const buttonItem = Array.from({ length: 200 }).map((item, index) =>
        <div key={index}
             title={calcHexColor(index)}
             className={{index}}
             style={{backgroundColor: calcHexColor(index)}}
             role={`item-${index}`}>__</div>
    )

    return (
        <div className="palette">
            {buttonItem}
        </div>
    );
}

export default Palette;
