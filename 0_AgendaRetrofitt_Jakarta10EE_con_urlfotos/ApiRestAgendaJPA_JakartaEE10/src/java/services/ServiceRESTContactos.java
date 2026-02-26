package services;

import bdagenda.Contactos;
import bdagenda.ContactosJpaController;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.List;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Path("contactos")
public class ServiceRESTContactos {

    private static final String PERSISTENCE_UNIT = "ApiRestAgendaJPAPU";
    private static final String PATH_IMAGENES = "datos/imagenes/contactos/";

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        HashMap<String, String> mensaje = new HashMap<>();
        Response response;
        Response.Status statusResul;

        List<Contactos> lista;
        try (EntityManagerFactory emf
                = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);) {
            ContactosJpaController dao = new ContactosJpaController(emf);
            lista = dao.findContactosEntities();
            if (lista == null) {
                statusResul = Response.Status.NO_CONTENT;
                response = Response
                        .status(statusResul)
                        .build();
            } else {
                statusResul = Response.Status.OK;
                response = Response
                        .status(statusResul)
                        .entity(lista)
                        .build();
            }
        } catch (Exception ex) {
            statusResul = Response.Status.BAD_REQUEST;
            mensaje.put("mensaje", "Error al procesar la petición " + ex.getLocalizedMessage());
            response = Response
                    .status(statusResul)
                    .entity(mensaje)
                    .build();
        }
        return response;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") int id) {
        HashMap<String, String> mensaje = new HashMap<>();
        Response response;
        Response.Status statusResul;
        Contactos contacto;
        try (EntityManagerFactory emf
                = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);) {
            ContactosJpaController dao = new ContactosJpaController(emf);
            contacto = dao.findContactos(id);

            if (contacto == null) {
                statusResul = Response.Status.NOT_FOUND;
                mensaje.put("mensaje", "No existe contacto con ID " + id);
                response = Response
                        .status(statusResul)
                        .entity(mensaje)
                        .build();
            } else {
                statusResul = Response.Status.OK;
                response = Response
                        .status(statusResul)
                        .entity(contacto)
                        .build();
            }
        } catch (Exception ex) {
            statusResul = Response.Status.BAD_REQUEST;
            mensaje.put("mensaje", "Error al procesar la petición " + ex.getLocalizedMessage());
            response = Response
                    .status(statusResul)
                    .entity(mensaje)
                    .build();
        }
        return response;
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getCuenta() {
        HashMap<String, String> mensaje = new HashMap<>();
        Response response;
        Response.Status statusResul;
        try (EntityManagerFactory emf
                = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);) {
            ContactosJpaController dao = new ContactosJpaController(emf);

            Long count = dao.getEntityManager()
                    .createQuery("SELECT COUNT(c) FROM Contactos c", Long.class)
                    .getSingleResult();

            statusResul = Response.Status.OK;
            response = Response
                    .status(statusResul)
                    .entity(count)
                    .build();

        } catch (Exception ex) {
            statusResul = Response.Status.BAD_REQUEST;
            mensaje.put("mensaje", "Error al procesar la petición " + ex.getLocalizedMessage());
            response = Response
                    .status(statusResul)
                    .entity(mensaje)
                    .build();
        }
        return response;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(Contactos contacto) {
        HashMap<String, String> mensaje = new HashMap<>();
        Response response;
        Response.Status statusResul;

        try (EntityManagerFactory emf
                = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);) {

            ContactosJpaController dao = new ContactosJpaController(emf);
            Contactos contactoFound = dao.findContactos(contacto.getId());
            if (contactoFound == null) {
                statusResul = Response.Status.NOT_FOUND;
                mensaje.put("mensaje", "No existe contacto con ID " + contacto.getId());
                response = Response
                        .status(statusResul)
                        .entity(mensaje)
                        .build();
            } else {
                contactoFound.setNombre(contacto.getNombre());
                contactoFound.setApellidos(contacto.getApellidos());
                contactoFound.setEmail(contacto.getEmail());
                contactoFound.setTelefono(contacto.getTelefono());
                contactoFound.setUrlFoto(contacto.getUrlFoto());
                contactoFound.setCategorias(contacto.getCategorias());

                dao.edit(contactoFound);
                statusResul = Response.Status.OK;
                mensaje.put("mensaje", "Libro con ID " + contacto.getId() + " actualizado");
                response = Response
                        .status(statusResul)
                        .entity(mensaje)
                        .build();
            }
        } catch (Exception ex) {
            statusResul = Response.Status.BAD_REQUEST;
            mensaje.put("mensaje", "Error al procesar la petición " + ex.getLocalizedMessage());
            response = Response
                    .status(statusResul)
                    .entity(mensaje)
                    .build();
        }
        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(Contactos contacto) {
        HashMap<String, String> mensaje = new HashMap<>();
        Response response;
        Response.Status statusResul;

        try (EntityManagerFactory emf
                = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);) {
            ContactosJpaController dao = new ContactosJpaController(emf);

            Contactos contactoFound = null;
            if ((contacto.getId() != 0) && (contacto.getId() != null)) {
                contactoFound = dao.findContactos(contacto.getId());
            }

            if (contactoFound != null) {
                statusResul = Response.Status.FOUND;
                mensaje.put("mensaje", "Ya existe contacto con ID " + contacto.getId());
                response = Response
                        .status(statusResul)
                        .entity(mensaje)
                        .build();
            } else {
                contacto.setUrlFoto(null);
                dao.create(contacto);
                statusResul = Response.Status.CREATED;
                mensaje.put("mensaje", "Contacto " + contacto.getNombre() + contacto.getApellidos() + " grabado");
                response = Response
                        .status(statusResul)
                        .entity(mensaje)
                        .build();
            }
        } catch (Exception ex) {
            statusResul = Response.Status.BAD_REQUEST;
            mensaje.put("mensaje", "Error al procesar la petición " + ex.getLocalizedMessage());
            response = Response
                    .status(statusResul)
                    .entity(mensaje)
                    .build();
        }
        return response;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        HashMap<String, String> mensaje = new HashMap<>();
        Response response;
        Response.Status statusResul;

        try (EntityManagerFactory emf
                = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);) {

            ContactosJpaController dao = new ContactosJpaController(emf);
            Contactos contactoFound = dao.findContactos(id);

            if (contactoFound == null) {
                statusResul = Response.Status.NOT_FOUND;
                mensaje.put("mensaje", "No existe contacto con ID " + id);
                response = Response
                        .status(statusResul)
                        .entity(mensaje)
                        .build();
            } else {
                String urlFoto = contactoFound.getUrlFoto();

                if (urlFoto != null && !urlFoto.isBlank()) {
                    String nombreImagen = urlFoto.substring(urlFoto.lastIndexOf('/') + 1);
                    File file = new File(PATH_IMAGENES + nombreImagen);
                    if (file.exists()) {
                        file.delete();
                    }
                }

                dao.destroy(id);
                statusResul = Response.Status.OK;
                mensaje.put("mensaje", "Contacto con ID " + id + " eliminado");
                response = Response
                        .status(statusResul)
                        .entity(mensaje)
                        .build();
            }
        } catch (Exception ex) {
            statusResul = Response.Status.BAD_REQUEST;
            mensaje.put("mensaje", "Error al procesar la petición " + ex.getLocalizedMessage());
            response = Response
                    .status(statusResul)
                    .entity(mensaje)
                    .build();
        }
        return response;
    }

    private String obtenUrlImagen(int id, File directory, DotosImagen datosImagen) throws IOException, Exception {
        String base64Image = datosImagen.base64Image();
        if (base64Image == null || base64Image.isEmpty()) {
            throw new Exception("La imagen no puede estar vacía.");
        }

        String extension = switch (base64Image.substring(0, Math.min(base64Image.length(), 5))) {
            case "/9j/" ->
                "jpg";
            case "iVBOR" ->
                "png";
            case "R0lG" ->
                "gif";
            case "UklC" ->
                "webp";
            default ->
                "bmp";
        };

        // 1. Formateamos el ID del contacto a 4 dígitos (ej: "id0001")
        String idPrefix = String.format("id%04d", id);
        int version = 1; // Versión por defecto si es la primera vez

        // 2. Buscamos archivos que empiecen por "idXXXX" en el directorio
        File[] existingFiles = directory.listFiles((dir, name) -> name.startsWith(idPrefix));

        if (existingFiles != null && existingFiles.length > 0) {
            int maxVersion = 0;

            // Recorremos los archivos encontrados (normalmente será uno, pero es más seguro así)
            for (File oldFile : existingFiles) {
                String oldFileName = oldFile.getName();
                int dotIndex = oldFileName.lastIndexOf('.');

                // La versión empieza en la posición 6 y termina en el punto de la extensión
                if (dotIndex > 6) {
                    try {
                        String versionStr = oldFileName.substring(6, dotIndex);
                        int currentVersion = Integer.parseInt(versionStr);
                        if (currentVersion > maxVersion) {
                            maxVersion = currentVersion;
                        }
                    } catch (NumberFormatException ignored) {
                        // Si falla el parseo de la versión por algún motivo, lo ignoramos
                    }
                }
                // 3. Borramos el archivo antiguo para no saturar el servidor
                oldFile.delete();
            }
            // Incrementamos la versión más alta que hayamos encontrado
            version = maxVersion + 1;
        }

        // 4. Generamos el nuevo nombre: prefijoID (6 chars) + versión a 6 dígitos + extensión
        // Ejemplo final: "id0001" + "000002" + ".png" -> id0001000002.png
        // Esto evita la caché agresiva del cliente pues se genera una nueva url en cada actualización
        String fileName = String.format("%s%06d.%s", idPrefix, version, extension);
        String filePath = directory.getAbsolutePath() + File.separator + fileName;
        File file = new File(filePath);

        byte[] imageBytes = Base64.getMimeDecoder().decode(base64Image);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(imageBytes);
        }

        return uriInfo.getBaseUriBuilder()
                .path(ServiceRESTContactos.class)
                .path(ServiceRESTContactos.class, "getImage")
                .build(fileName)
                .toString();
    }

    public record DotosImagen(String extension, String base64Image) {

    }

    @PATCH
    @Path("/uploadfoto/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadImage(@PathParam("id") int id, DotosImagen datosImagen) {
        Response response;
        HashMap<String, String> mensaje = new HashMap<>();

        try {
            if (datosImagen != null) {
                File directory = new File(PATH_IMAGENES);
                if (directory.exists() || directory.mkdirs()) {
                    try (EntityManagerFactory emf
                            = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);) {
                        ContactosJpaController dao = new ContactosJpaController(emf);

                        Contactos contactoFound = dao.findContactos(id);

                        if (contactoFound == null) {
                            mensaje.put("mensaje", "No existe el contacto con ID " + id);
                            response = Response
                                    .status(Response.Status.NOT_FOUND)
                                    .entity(mensaje)
                                    .build();
                        } else {
                            // La imagen se guardará por ejemplo como tomcat/bin/datos/imagenes/contactos/id000001.png
                            String imageUrl = obtenUrlImagen(id, directory, datosImagen);
                            contactoFound.setUrlFoto(imageUrl);
                            dao.edit(contactoFound);
                            mensaje.put("mensaje", imageUrl);
                            return Response.status(Response.Status.OK)
                                    .entity(mensaje)
                                    .build();
                        }
                    } catch (Exception ex) {
                        mensaje.put("mensaje", "Error al procesar la petición " + ex.getLocalizedMessage());
                        response = Response
                                .status(Response.Status.BAD_REQUEST)
                                .entity(mensaje)
                                .build();
                    }
                } else {
                    mensaje.put("mensaje", "Error: No se pudo crear el directorio " + directory.getAbsolutePath());
                    response = Response.serverError()
                            .entity(mensaje)
                            .build();
                }
            } else {
                mensaje.put("mensaje", "No hay imagen a subir");
                response = Response.status(Response.Status.BAD_REQUEST)
                        .entity(mensaje)
                        .build();
            }

        } catch (IllegalArgumentException e) {
            mensaje.put("mensaje", "Los datos no es una imagen en base64 " + e.getLocalizedMessage());
            response = Response.status(Response.Status.BAD_REQUEST)
                    .entity(mensaje)
                    .build();
        }

        return response;
    }

    @GET
    @Path("/{nombreImagen}")
    @Produces("image/*")
    public Response getImage(@PathParam("nombreImagen") String nombreImagen) {
        Response response;
        HashMap<String, String> mensaje = new HashMap<>();

        try {
            File file = new File(PATH_IMAGENES + nombreImagen);

            if (file.exists() && file.isFile()) {
                byte[] imageBytes = Files.readAllBytes(file.toPath());

                String contentType = Files.probeContentType(file.toPath());

                if (contentType == null) {
                    if (nombreImagen.toLowerCase().endsWith(".jpg") || nombreImagen.toLowerCase().endsWith(".jpeg")) {
                        contentType = "image/jpeg";
                    } else if (nombreImagen.toLowerCase().endsWith(".png")) {
                        contentType = "image/png";
                    } else if (nombreImagen.toLowerCase().endsWith(".gif")) {
                        contentType = "image/gif";
                    } else if (nombreImagen.toLowerCase().endsWith(".webp")) {
                        contentType = "image/webp";
                    } else if (nombreImagen.toLowerCase().endsWith(".bmp")) {
                        contentType = "image/bmp";
                    } else {
                        contentType = "application/octet-stream";
                    }
                }

                response = Response.ok(imageBytes)
                        .header("Content-Type", contentType)
                        .build();
            } else {
                response = Response.status(Response.Status.NOT_FOUND).build();
            }

        } catch (IOException e) {
            mensaje.put("mensaje", "Error al procesar la petición: " + e.getLocalizedMessage());
            response = Response.serverError().entity(mensaje).build();
        }

        return response;

    }

}
