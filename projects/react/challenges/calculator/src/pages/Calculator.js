import './Calculator.css';
import {useEffect, useState, useReducer} from "react";
import CalculatorEntity from "./CalculatorEntity";

function Calculator() {
  const {calcStatus, setFirstNumber} = CalculatorEntity()

  const [state, updateState] = useReducer(
    (state, updates) => ({...state, ...updates}), calcStatus
  );
  const [displayMessage, setDisplayMessage] = useState(0)
  const onload = () => console.log("starting calculator")
  // the useEffect is a hook function that is listening for some properties, and if the properties changes, the hook is raised
  useEffect(() => {
    setDisplayMessage((prevState) => state.lastNumber)
  }, [state.lastNumber]);
  useEffect(() => {
    setDisplayMessage((prevState) => state.bufferNumber)
  }, [state.bufferNumber, state.operation]);
  useEffect(() => {
    onload()
  }, []);
  const updateStatus = (result, operation) => {
    updateState({'lastNumber': 0})
    updateState({'operation': operation})
    updateState({'bufferNumber': result})
    setDisplayMessage((prevState) => result)
  }
  const updateNumber = (value) => updateState({'lastNumber': ~~([state.lastNumber.toString() + value.toString()].join(''))})

  const calcBinaryOperation = () => {
    let result = 0
    switch (state.operation) {
      case '+':
        result = state.bufferNumber + state.lastNumber
        break
      case '-':
        result = state.bufferNumber - state.lastNumber
        break
      case '*':
        result = state.bufferNumber * state.lastNumber
        break
      case '/':
        result = state.bufferNumber / state.lastNumber
        break
      case '%':
        result = state.bufferNumber / 100
        break
      case '=':
        result = state.bufferNumber
        break
      case '':
        result = state.lastNumber
        break
      case '+/-':
        result = state.bufferNumber
        break
    }
    return result
  }
  const applyPreviousOperation = () => calcBinaryOperation()

  const setEqualsOperation = (operation) => {
    const result = applyPreviousOperation()
    updateStatus(result, operation)
  }
  const setBinaryOperation = (operation) => {
    const result = applyPreviousOperation()
    updateStatus(result, operation)
  }

  const setPlusMinusOperation = (operation) => {
    if (state.lastNumber === 0) {
      return
    }
    const result = applyPreviousOperation()
    updateStatus(-result, operation)
  }

  const calcButton = (label, func) => Object.assign([], {label}, {func})

  const buttons = [
    calcButton('AC', () => updateState(calcStatus)),
    calcButton('+/-', () => setPlusMinusOperation('+/-')),
    calcButton('%', () => setBinaryOperation('%')),
    calcButton('/', () => setBinaryOperation('/')),
    calcButton('7', () => updateNumber(7)),
    calcButton('8', () => updateNumber(8)),
    calcButton('9', () => updateNumber(9)),
    calcButton('*', () => setBinaryOperation('*')),
    calcButton('4', () => updateNumber(4)),
    calcButton('5', () => updateNumber(5)),
    calcButton('6', () => updateNumber(6)),
    calcButton('-', () => setBinaryOperation('-')),
    calcButton('1', () => updateNumber(1)),
    calcButton('2', () => updateNumber(2)),
    calcButton('3', () => updateNumber(3)),
    calcButton('+', () => setBinaryOperation('+')),
    calcButton('0', () => updateNumber(0)),
    calcButton('.', () => setBinaryOperation('.')),
    calcButton('=', () => setEqualsOperation('='))]

  const itemButton = buttons.map((item, index) =>
    <div key={item.label} className='buttons'
         onClick={item.func} role={item.label}
    >{item.label}</div>
  )

  const display = <div role="display" className='display'>{displayMessage}</div>
  return (
    <div>
      {display}
      <div>
        <div className="box">{itemButton}</div>
      </div>
    </div>
  );
}

export default Calculator;
