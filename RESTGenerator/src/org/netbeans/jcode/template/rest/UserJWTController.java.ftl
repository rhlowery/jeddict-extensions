<#if package??>package ${package};</#if>

import ${JWTToken_FQN};
import ${Constants_FQN};
import ${entityPackage}.User;
import javax.inject.Inject;
import ${LoginDTO_FQN};
import javax.servlet.ServletException;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import ${AuthenticationException_FQN};
import ${TokenProvider_FQN};
import ${UserAuthenticationToken_FQN};
import ${UserService_FQN};
import javax.validation.Valid;
<#if metrics>import com.codahale.metrics.annotation.Timed;</#if>
<#if docs>import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;</#if>

<#if docs>@Api(value = "/api")</#if>
@Path("/api")
public class ${restPrefix}UserJWT${restSuffix} {

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private UserService userService;

    /**
     * POST /authenticate : authenticate the credential.
     * <p>
     * Atuhenticate the user login and password.
     * </p>
     *
     * @param loginDTO the login details to authenticate
     * @return the Response with status 200 (OK) and with body the
     * new jwt token, or with status 401 (Unauthorized) if the authentication fails
     */
    <#if metrics>@Timed</#if>
    <#if docs>@ApiOperation(value = "authenticate the credential" )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 401, message = "Unauthorized")})</#if>
    @Path("/authenticate")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginDTO loginDTO) throws ServletException {

        UserAuthenticationToken authenticationToken = new UserAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

        try {
            User user = userService.authenticate(authenticationToken);
            boolean rememberMe = (loginDTO.isRememberMe() == null) ? false : loginDTO.isRememberMe();
            String jwt = tokenProvider.createToken(user, rememberMe);
            return Response.ok(new JWTToken(jwt)).header(Constants.AUTHORIZATION_HEADER, "Bearer " + jwt).build();
        } catch (AuthenticationException exception) {
            return Response.status(Status.UNAUTHORIZED).header("AuthenticationException", exception.getLocalizedMessage()).build();
        }
    }

}