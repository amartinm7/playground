const CalculatorEntity = (func) => {

  const calcStatus = {
    lastNumber: 0,
    bufferNumber: 0,
    operation: '',
    sign: "+",
    isDotDecimal: false
  }

  return {
    calcStatus
  }
}

export default CalculatorEntity;
