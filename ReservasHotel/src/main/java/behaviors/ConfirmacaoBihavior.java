package behaviors;

import javax.faces.application.ResourceDependency;
import javax.faces.component.behavior.ClientBehaviorBase;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.component.behavior.FacesBehavior;

@FacesBehavior(value = "behaviors.Confirmacao")
@ResourceDependency(name = "confirmacao.js", library = "js", target = "head")
public class ConfirmacaoBihavior extends ClientBehaviorBase {

	@Override
	public String getScript(ClientBehaviorContext behaviorContext) {
		String excluirCodigoPessoa = behaviorContext.getComponent().getAttributes().get("codigo") + "";
		return "return confirmar('Deseja realmente excluir o registro " + excluirCodigoPessoa + "?')";
	}

}
