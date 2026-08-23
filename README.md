# Legacy Java Modernization Lab

## Objective
Learn to use GitHub Copilot inside **IntelliJ IDEA** to complete a realistic Danaher/Cepheid engineering task.

## Duration
40-45 minutes

## Prerequisites
- IntelliJ IDEA (Community or Ultimate)
- JDK 17+
- Maven 3.9+
- GitHub Copilot and GitHub Copilot Chat plugins enabled in IntelliJ
- Git

## IntelliJ Setup
1. Open IntelliJ IDEA and choose **File > Open**, then select this repository folder.
2. Trust the project when prompted and let IntelliJ auto-import the Maven project.
3. Confirm the Project SDK is Java 17 in **File > Project Structure > Project**.
4. Open the **Maven** tool window (right sidebar) to run 	est/erify goals.
5. Open **GitHub Copilot Chat** from the right tool bar, keep the relevant file active so Copilot has context.

## Scenario
CepheidDx has a legacy order-processing module (`LegacyOrderProcessor`) written years ago with duplicated discount logic, deep nesting, and weak exception handling. It still works, but it is hard to change safely.

## Starting Point
`LegacyOrderProcessor` has one long method (`processOrder`) with duplicated region/payment discount logic, a broad `catch (Exception e)`, and only one shallow test. `calculateTotal` also has minor duplication.

## Hands-on Tasks
1. Ask Copilot to explain `processOrder` and list the technical debt it finds (duplication, nesting, naming, exception handling).
2. Ask Copilot to generate a short modernization plan ranked by risk/impact, without applying changes yet.
3. Ask Copilot for a refactoring prompt that extracts the discount logic into a small `DiscountPolicy` class or method Ã¢â‚¬" review the proposal before accepting.
4. Apply the refactor to **one** selected area only (do not let Copilot modernize the whole class at once).
5. Generate or update JUnit 5 tests that cover US-WEST, US-EAST, and default regions with CREDIT/INVOICE/other payment types.
6. Run the full test suite and confirm behavior is unchanged from before the refactor.

## Validation
Run from the IntelliJ **Terminal** tab (Alt+F12) or the Maven tool window:

```bash
mvn test
```

## Expected Result
The refactored code behaves identically to the original for all payment/region combinations, with clearer structure and better test coverage.

## Troubleshooting
- If tests fail after refactoring, compare outputs for each region/payment combination against the original method logic.
- If Copilot proposes rewriting the entire class, ask it to scope the change to a single method.

## Optional Challenge
Replace the broad `catch (Exception e)` with a specific, meaningful exception type and add a test that verifies the new behavior.

## Copilot Customization Guide

**Already provided:** `.github/instructions/copilot-instructions.md` - enforces small, tested, behavior-preserving changes.

**New prompt file:** `.github/prompts/modernization-plan-prompt.md`

```
Role: Java maintainer of LegacyOrderProcessor
Task: List technical debt, rank by risk, propose smallest safe refactor
Constraints: one method at a time; keep behavior identical; add tests first
Output: ranked list + 3-step plan, no code yet
```

**Optional stretch tasks (build these yourself - not provided):**
- Custom agent: create `.github/agents/legacy-modernization-agent.agent.md` defining a persona that proposes one small, behavior-preserving refactor at a time and blocks full-file rewrites.
- Skill: create `.github/skills/safe-refactor-checklist/SKILL.md` capturing a reusable checklist for incremental legacy refactors backed by tests.
- `AGENTS.md`: optionally add a root-level file summarizing repo conventions for cross-tool agent compatibility (Copilot CLI and other agentic tools read this file).

**Enterprise tip:** Enterprise Copilot usage favors small, structured, reusable prompts over long free-form ones. State `Role / Task / Constraints / Output` in under ~5 lines - this keeps token usage low and responses focused, which matters when Copilot is used constantly across a team.
