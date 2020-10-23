import React from 'react';
import { connect } from 'react-redux';
import AndGate from '../../../components/SmartComponent/LogicGate/AndGate';
import Clock from '../../../components/SmartComponent/Signal/Clock';
import ComponentDemux from '../ComponentDemux';
import NotGate from '../../../components/SmartComponent/LogicGate/NotGate';
import XorGate from '../../../components/SmartComponent/LogicGate/XorGate';
import OrGate from '../../../components/SmartComponent/LogicGate/OrGate/OrGate';
import NandGate from '../../../components/SmartComponent/LogicGate/NandGate';
import NorGate from '../../../components/SmartComponent/LogicGate/NorGate';
import XnorGate from '../../../components/SmartComponent/LogicGate/XnorGate';
import DFlipFlop from '../../../components/SmartComponent/Memory/DFlipFlop';
import TFlipFlop from '../../../components/SmartComponent/Memory/TFlipFlop';
import JKFlipFlop from '../../../components/SmartComponent/Memory/JKFlipFlop';
import RSFlipFlop from '../../../components/SmartComponent/Memory/RSFlipFlop';
import Source from '../../../components/SmartComponent/Wiring/Source';
import InputButton from '../../../components/SmartComponent/Signal/InputButton';

/**
 * Determines what to render after the ComponentDemux asserts what type a component is.
 * @param {*} componentReducer
 * @author: kyhorne, kajhemmingsen
 */
const ComponentFactory = ({ componentReducer }) => componentReducer.grid.map((component) => {
  const { type, x, y } = component;
  return (
    <ComponentDemux
      type={type}
      andGate={<AndGate x={x} y={y} />}
      clock={<Clock x={x} y={y} />}
      notGate={<NotGate x={x} y={y} />}
      xorGate={<XorGate x={x} y={y} />}
      orGate={<OrGate x={x} y={y} />}
      nandGate={<NandGate x={x} y={y} />}
      norGate={<NorGate x={x} y={y} />}
      xnorGate={<XnorGate x={x} y={y} />}
      dFlipFlop={<DFlipFlop x={x} y={y} />}
      tFlipFlop={<TFlipFlop x={x} y={y} />}
      jkFlipFlop={<JKFlipFlop x={x} y={y} />}
      rsFlipFlop={<RSFlipFlop x={x} y={y} />}
      source={<Source x={x} y={y} />}
      inputButton={<InputButton x={x} y={y} />}
    />
  );
});

const mapStateToProps = (state) => ({
  componentReducer: state.componentReducer,
});

export default connect(mapStateToProps)(ComponentFactory);
