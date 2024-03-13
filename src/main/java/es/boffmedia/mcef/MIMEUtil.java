package es.boffmedia.mcef;

public final class MIMEUtil {
    public static String mimeFromExtension(String ext) {
        String result;
        switch (ext) {
            case "htm":
            case "html":
                result = "text/html";
                break;
            case "css":
                result = "text/css";
                break;
            case "pdf":
                result = "application/pdf";
                break;
            case "xz":
                result = "application/x-xz";
                break;
            case "tar":
                result = "application/x-tar";
                break;
            case "cpio":
                result = "application/x-cpio";
                break;
            case "7z":
                result = "application/x-7z-compressed";
                break;
            case "zip":
                result = "application/zip";
                break;
            case "js":
                result = "text/javascript";
                break;
            case "json":
                result = "application/json";
                break;
            case "jsonml":
                result = "application/jsonml+json";
                break;
            case "jar":
                result = "application/java-archive";
                break;
            case "ser":
                result = "application/java-serialized-object";
                break;
            case "class":
                result = "application/java-vm";
                break;
            case "wad":
                result = "application/x-doom";
                break;
            case "png":
                result = "image/png";
                break;
            case "jpg":
            case "jpeg":
                result = "image/jpeg";
                break;
            case "gif":
                result = "image/gif";
                break;
            case "svg":
                result = "image/svg+xml";
                break;
            case "xml":
                result = "text/xml";
                break;
            case "txt":
                result = "text/plain";
                break;
            case "oga":
            case "ogg":
            case "spx":
                result = "audio/ogg";
                break;
            case "mp4":
            case "mp4v":
            case "mpg4":
                result = "video/mp4";
                break;
            case "m4a":
            case "mp4a":
                result = "audio/mp4";
                break;
            case "mid":
            case "midi":
            case "kar":
            case "rmi":
                result = "audio/midi";
                break;
            case "mpga":
            case "mp2":
            case "mp2a":
            case "mp3":
            case "mp3a":
            case "m2a":
                result = "audio/mpeg";
                break;
            case "mpeg":
            case "mpg":
            case "mpe":
            case "m1v":
            case "m2v":
                result = "video/mpeg";
                break;
            case "jpgv":
                result = "video/jpeg";
                break;
            case "h264":
                result = "video/h264";
                break;
            case "h261":
                result = "video/h261";
                break;
            case "h263":
                result = "video/h263";
                break;
            case "webm":
                result = "video/webm";
                break;
            case "flv":
                result = "video/flv";
                break;
            case "m4v":
                result = "video/m4v";
                break;
            case "qt":
            case "mov":
                result = "video/quicktime";
                break;
            case "ogv":
                result = "video/ogg";
                break;
            default:
                result = null;
                break;
        }
        return result;
    }
}