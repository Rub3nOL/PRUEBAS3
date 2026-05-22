-- Trigger 1: Registrar automáticamente la fecha de alta al insertar material --

DELIMITER //
CREATE TRIGGER trg_fecha_alta_auto
BEFORE INSERT ON material
FOR EACH ROW
BEGIN
    IF NEW.fecha_alta IS NULL THEN
        SET NEW.fecha_alta = CURDATE();
    END IF;
END//
DELIMITER ;




