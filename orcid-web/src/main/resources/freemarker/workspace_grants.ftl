<#--

    =============================================================================

    ORCID (R) Open Source
    http://orcid.org

    Copyright (c) 2012-2013 ORCID, Inc.
    Licensed under an MIT-Style License (MIT)
    http://orcid.org/open-source-license

    This copyright and license information (including a link to the full license)
    shall be included in its entirety in all copies or substantial portion of
    the software.

    =============================================================================

-->
<ul class="workspace-fundings privacy-list">
	<li>
		<@orcid.privacy "hello" />
		<h4 class="grant-title"><a href="#">${springMacroRequestContext.getMessage("workspace_fundings.Loremipsumdolorsitamet")}</a> <span class="label label-clear"><b>${springMacroRequestContext.getMessage("workspace_fundings.Updatedby")}</b> ${springMacroRequestContext.getMessage("workspace_fundings.BrownUniversity")}</span></h4>
		<p>${springMacroRequestContext.getMessage("workspace_fundings.Loremipsum")}</p>
	</li>
	<li>
		<@orcid.privacy "hello" "private" />
		<h4 class="grant-title"><a href="#">${springMacroRequestContext.getMessage("workspace_fundings.Loremipsumdolorsitamet")}</a> <span class="label label-clear"><b>${springMacroRequestContext.getMessage("workspace_fundings.Updatedby")}</b> ${springMacroRequestContext.getMessage("workspace_fundings.BrownUniversity")}</span></h4>
		<p>${springMacroRequestContext.getMessage("workspace_fundings.Loremipsum")}</p>
	</li>
</ul>
