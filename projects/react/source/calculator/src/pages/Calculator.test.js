import * as React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';

import Calculator from './Calculator';

describe('Calculator', () => {
  it('renders Calculator: init', () => {
    render(<Calculator />);
    screen.debug();
    expect(screen.getByRole('display')).toBeInTheDocument();
    expect(screen.getByRole('display')).toHaveTextContent(0)
  });

  it('renders buttons Calculator', () => {
    render(<Calculator />);
    screen.debug();
    expect(screen.getByRole('display')).toBeInTheDocument();
    expect(screen.getByRole('display')).toHaveTextContent('0')
    expect(screen.getByRole('1')).toHaveTextContent('1')
    expect(screen.getByRole('2')).toHaveTextContent('2')
    expect(screen.getByRole('3')).toHaveTextContent('3')
    expect(screen.getByRole('4')).toHaveTextContent('4')
    expect(screen.getByRole('5')).toHaveTextContent('5')
    expect(screen.getByRole('6')).toHaveTextContent('6')
    expect(screen.getByRole('7')).toHaveTextContent('7')
    expect(screen.getByRole('8')).toHaveTextContent('8')
    expect(screen.getByRole('9')).toHaveTextContent('9')
    expect(screen.getByRole('+')).toHaveTextContent('+')
    expect(screen.getByRole('-')).toHaveTextContent('-')
    expect(screen.getByRole('*')).toHaveTextContent('*')
    expect(screen.getByRole('/')).toHaveTextContent('/')
    expect(screen.getByRole('%')).toHaveTextContent('%')
    expect(screen.getByRole('AC')).toHaveTextContent('AC')
    expect(screen.getByRole('+/-')).toHaveTextContent('+/-')
    expect(screen.getByRole('.')).toHaveTextContent('.')
  });

  it('renders Calculator: 1 + 2 = 3', () => {
    render(<Calculator />);
    screen.debug();
    expect(screen.getByRole('display')).toBeInTheDocument();
    expect(screen.getByRole('display')).toHaveTextContent('0')
    expect(screen.getByRole('1')).toHaveTextContent('1')
    expect(screen.getByRole('+')).toHaveTextContent('+')
    expect(screen.getByRole('2')).toHaveTextContent('2')
    fireEvent.click(screen.getByRole('1'))
    expect(screen.getByRole('display')).toHaveTextContent('1')
    fireEvent.click(screen.getByRole('+'))
    expect(screen.getByRole('display')).toHaveTextContent('1')
    fireEvent.click(screen.getByRole('2'))
    expect(screen.getByRole('display')).toHaveTextContent('2')
    fireEvent.click(screen.getByRole('='))
    expect(screen.getByRole('display')).toHaveTextContent('3')
  });

  it('renders Calculator: 1 + 2 = 3 + 1 + 1', () => {
    render(<Calculator />);
    screen.debug();
    expect(screen.getByRole('display')).toBeInTheDocument();
    expect(screen.getByRole('display')).toHaveTextContent('0')
    expect(screen.getByRole('1')).toHaveTextContent('1')
    expect(screen.getByRole('+')).toHaveTextContent('+')
    expect(screen.getByRole('2')).toHaveTextContent('2')
    fireEvent.click(screen.getByRole('1'))
    expect(screen.getByRole('display')).toHaveTextContent('1')
    fireEvent.click(screen.getByRole('+'))
    expect(screen.getByRole('display')).toHaveTextContent('1')
    fireEvent.click(screen.getByRole('2'))
    expect(screen.getByRole('display')).toHaveTextContent('2')
    fireEvent.click(screen.getByRole('='))
    expect(screen.getByRole('display')).toHaveTextContent('3')
    fireEvent.click(screen.getByRole('+'))
    expect(screen.getByRole('display')).toHaveTextContent('3')
    fireEvent.click(screen.getByRole('1'))
    expect(screen.getByRole('display')).toHaveTextContent('1')
    fireEvent.click(screen.getByRole('+'))
    expect(screen.getByRole('display')).toHaveTextContent('4')
    fireEvent.click(screen.getByRole('1'))
    expect(screen.getByRole('display')).toHaveTextContent('1')
    fireEvent.click(screen.getByRole('+'))
    expect(screen.getByRole('display')).toHaveTextContent('5')
  });

  it('renders Calculator: 2 - 1 + 3 * 1 / 1 =', () => {
    render(<Calculator />);
    screen.debug();
    expect(screen.getByRole('display')).toBeInTheDocument();
    expect(screen.getByRole('display')).toHaveTextContent('0')
    expect(screen.getByRole('1')).toHaveTextContent('1')
    expect(screen.getByRole('+')).toHaveTextContent('+')
    expect(screen.getByRole('2')).toHaveTextContent('2')
    fireEvent.click(screen.getByRole('2'))
    expect(screen.getByRole('display')).toHaveTextContent('2')
    fireEvent.click(screen.getByRole('-'))
    expect(screen.getByRole('display')).toHaveTextContent('2')
    fireEvent.click(screen.getByRole('1'))
    expect(screen.getByRole('display')).toHaveTextContent('1')
    fireEvent.click(screen.getByRole('+'))
    expect(screen.getByRole('display')).toHaveTextContent('1')
    fireEvent.click(screen.getByRole('3'))
    expect(screen.getByRole('display')).toHaveTextContent('3')
    fireEvent.click(screen.getByRole('*'))
    expect(screen.getByRole('display')).toHaveTextContent('4')
    fireEvent.click(screen.getByRole('1'))
    expect(screen.getByRole('display')).toHaveTextContent('1')
    fireEvent.click(screen.getByRole('/'))
    expect(screen.getByRole('display')).toHaveTextContent('4')
    fireEvent.click(screen.getByRole('1'))
    expect(screen.getByRole('display')).toHaveTextContent('1')
    fireEvent.click(screen.getByRole('='))
    expect(screen.getByRole('display')).toHaveTextContent('4')
  });

  it('renders Calculator: 5 % =', () => {
    render(<Calculator />);
    screen.debug();
    expect(screen.getByRole('display')).toBeInTheDocument();
    expect(screen.getByRole('display')).toHaveTextContent('0')
    fireEvent.click(screen.getByRole('5'))
    expect(screen.getByRole('display')).toHaveTextContent('5')
    fireEvent.click(screen.getByRole('%'))
    expect(screen.getByRole('display')).toHaveTextContent('5')
    fireEvent.click(screen.getByRole('='))
    expect(screen.getByRole('display')).toHaveTextContent('0.05')
  });

  it('renders Calculator: 50 % * 20', () => {
    render(<Calculator />);
    screen.debug();
    expect(screen.getByRole('display')).toBeInTheDocument();
    expect(screen.getByRole('display')).toHaveTextContent('0')
    fireEvent.click(screen.getByRole('5'))
    expect(screen.getByRole('display')).toHaveTextContent('5')
    fireEvent.click(screen.getByRole('0'))
    expect(screen.getByRole('display')).toHaveTextContent('50')
    fireEvent.click(screen.getByRole('%'))
    expect(screen.getByRole('display')).toHaveTextContent('50')
    fireEvent.click(screen.getByRole('*'))
    expect(screen.getByRole('display')).toHaveTextContent('0.5')
    fireEvent.click(screen.getByRole('2'))
    expect(screen.getByRole('display')).toHaveTextContent('2')
    fireEvent.click(screen.getByRole('0'))
    expect(screen.getByRole('display')).toHaveTextContent('20')
    fireEvent.click(screen.getByRole('='))
    expect(screen.getByRole('display')).toHaveTextContent('10')
  });

  it('renders Calculator: 5 - + 5 = 0', () => {
    render(<Calculator />);
    screen.debug();
    expect(screen.getByRole('display')).toBeInTheDocument();
    expect(screen.getByRole('display')).toHaveTextContent('0')
    fireEvent.click(screen.getByRole('5'))
    expect(screen.getByRole('display')).toHaveTextContent('5')
    fireEvent.click(screen.getByRole('+/-'))
    expect(screen.getByRole('display')).toHaveTextContent('-5')
    fireEvent.click(screen.getByRole('+'))
    expect(screen.getByRole('display')).toHaveTextContent('-5')
    fireEvent.click(screen.getByRole('5'))
    expect(screen.getByRole('display')).toHaveTextContent('5')
    fireEvent.click(screen.getByRole('='))
    expect(screen.getByRole('display')).toHaveTextContent('0')
  });

  it('renders Calculator: 5 - * 5 = -25', () => {
    render(<Calculator />);
    screen.debug();
    expect(screen.getByRole('display')).toBeInTheDocument();
    expect(screen.getByRole('display')).toHaveTextContent('0')
    fireEvent.click(screen.getByRole('5'))
    expect(screen.getByRole('display')).toHaveTextContent('5')
    fireEvent.click(screen.getByRole('+/-'))
    expect(screen.getByRole('display')).toHaveTextContent('-5')
    fireEvent.click(screen.getByRole('*'))
    expect(screen.getByRole('display')).toHaveTextContent('-5')
    fireEvent.click(screen.getByRole('5'))
    expect(screen.getByRole('display')).toHaveTextContent('5')
    fireEvent.click(screen.getByRole('='))
    expect(screen.getByRole('display')).toHaveTextContent('-25')
  });

  it('renders Calculator: - 5 * 5 = 25', () => {
    render(<Calculator />);
    screen.debug();
    expect(screen.getByRole('display')).toBeInTheDocument();
    expect(screen.getByRole('display')).toHaveTextContent('0')
    fireEvent.click(screen.getByRole('+/-'))
    expect(screen.getByRole('display')).toHaveTextContent('0')
    fireEvent.click(screen.getByRole('5'))
    expect(screen.getByRole('display')).toHaveTextContent('5')
    fireEvent.click(screen.getByRole('*'))
    expect(screen.getByRole('display')).toHaveTextContent('5')
    fireEvent.click(screen.getByRole('5'))
    expect(screen.getByRole('display')).toHaveTextContent('5')
    fireEvent.click(screen.getByRole('='))
    expect(screen.getByRole('display')).toHaveTextContent('25')
  });
});
