import {
  AND_GATE,
  THREE_INPUT_AND_GATE,
  THREE_INPUT_NOR_GATE,
  THREE_INPUT_OR_GATE,
  THREE_INPUT_NAND_GATE,
  CLOCK,
  NOT_GATE,
  XOR_GATE,
  OR_GATE,
  NAND_GATE,
  NOR_GATE,
  XNOR_GATE,
  D_FLIP_FLOP,
  T_FLIP_FLOP,
  JK_FLIP_FLOP,
  RS_FLIP_FLOP,
  SOURCE,
  INPUT_BUTTON,
} from '../../../store/component/types';
/**
 * Takes the component type of a component being placed onto the grid.
 * Returns a corresponding type to ouput the desired shape.
 * @param {*} type the component type that is being passed in.
 * @author:kyhorne, kajhemmingsen
 */
const ComponentDemux = ({
  type,
  andGate,
  threeInputAndGate,
  threeInputNorGate,
  threeInputOrGate,
  threeInputNandGate,
  clock,
  notGate,
  xorGate,
  orGate,
  nandGate,
  norGate,
  xnorGate,
  dFlipFlop,
  tFlipFlop,
  jkFlipFlop,
  rsFlipFlop,
  source,
  inputButton,
}) => {
  switch (type) {
    case AND_GATE:
      return andGate;
    case THREE_INPUT_AND_GATE:
      return threeInputAndGate;
    case THREE_INPUT_NOR_GATE:
      return threeInputNorGate;
    case THREE_INPUT_OR_GATE:
      return threeInputOrGate;
    case THREE_INPUT_NAND_GATE:
      return threeInputNandGate;
    case CLOCK:
      return clock;
    case NOT_GATE:
      return notGate;
    case XOR_GATE:
      return xorGate;
    case OR_GATE:
      return orGate;
    case NAND_GATE:
      return nandGate;
    case NOR_GATE:
      return norGate;
    case XNOR_GATE:
      return xnorGate;
    case D_FLIP_FLOP:
      return dFlipFlop;
    case T_FLIP_FLOP:
      return tFlipFlop;
    case JK_FLIP_FLOP:
      return jkFlipFlop;
    case RS_FLIP_FLOP:
      return rsFlipFlop;
    case SOURCE:
      return source;
    case INPUT_BUTTON:
      return inputButton;
    default:
      throw new Error(`${type}: is an unimplemented type in the ComponentDemux`);
  }
};

export default ComponentDemux;
