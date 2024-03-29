import {
  AND_GATE,
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
  JK_FLIP_FLOP_PRE_CLR,
  RS_FLIP_FLOP,
  SOURCE,
  INPUT_BUTTON,
  HALF_ADDER,
  FULL_ADDER,
  HALF_SUBTRACTOR,
  FULL_SUBTRACTOR,
  EIGHT_TO_THREE_ENCODER,
  THREE_TO_EIGHT_DECODER,
  TWO_TO_ONE_MUX,
  FOUR_TO_ONE_MUX,
  ONE_TO_TWO_DEMUX,
  ONE_TO_FOUR_DEMUX,
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
  jkFlipFlopPRECLR,
  rsFlipFlop,
  halfAdder,
  fullAdder,
  halfSubtractor,
  fullSubtractor,
  eighttoThreeEncoder,
  threetoEightDecoder,
  twotoOneMux,
  fourtoOneMux,
  onetoTwoDemux,
  onetoFourDemux,
  source,
  inputButton,
}) => {
  switch (type) {
    case AND_GATE:
      return andGate;
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
    case JK_FLIP_FLOP_PRE_CLR:
      return jkFlipFlopPRECLR;
    case RS_FLIP_FLOP:
      return rsFlipFlop;
    case HALF_ADDER:
      return halfAdder;
    case FULL_ADDER:
      return fullAdder;
    case HALF_SUBTRACTOR:
      return halfSubtractor;
    case FULL_SUBTRACTOR:
      return fullSubtractor;
    case EIGHT_TO_THREE_ENCODER:
      return eighttoThreeEncoder;
    case THREE_TO_EIGHT_DECODER:
      return threetoEightDecoder;
    case TWO_TO_ONE_MUX:
      return twotoOneMux;
    case FOUR_TO_ONE_MUX:
      return fourtoOneMux;
    case ONE_TO_TWO_DEMUX:
      return onetoTwoDemux;
    case ONE_TO_FOUR_DEMUX:
      return onetoFourDemux;
    case SOURCE:
      return source;
    case INPUT_BUTTON:
      return inputButton;
    default:
      throw new Error(`${type}: is an unimplemented type in the ComponentDemux`);
  }
};

export default ComponentDemux;
